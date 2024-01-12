package com.wzh086.servlet;

import com.wzh086.model.Goods;
import com.wzh086.service.GoodsService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Goods goods = new Goods();
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        int pageNumber = 1;
        int type = 0;
        int goodsid = 0;
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getAttribute("pageNumber").toString());
        }
        if(request.getAttribute("type") != null){
            type = Integer.parseInt(request.getAttribute("type").toString());
        }

        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for(FileItem fileItem : fileItems){
                if(fileItem.isFormField()){
                    switch (fileItem.getFieldName()){
                        case "id":
                            goods = gService.getGoodsById(Integer.parseInt(fileItem.getString()));
                            //goods.setId(Integer.parseInt(fileItem.getString()));
                            break;
                        case "name":
                            goods.setName(fileItem.getString("utf-8"));
                            break;
                        case "price":
                            if (!fileItem.getString("utf-8").matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")) {
                                goods.setPrice(-2);
                            } else {
                                goods.setPrice(Float.valueOf(fileItem.getString("utf-8")));
                            }
                            break;
                        case "intro":
                            goods.setIntro(fileItem.getString("utf-8"));
                            break;
                        case "stock":
                            if (!fileItem.getString("utf-8").matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")) {
                                goods.setStock(-1);
                            } else {
                                goods.setStock(Integer.valueOf(fileItem.getString()));
                            }
                            break;
                        case "typeid":
                            goods.setType_id(Integer.valueOf(fileItem.getString("utf-8")));
                            break;
                    }
                }else{
                    if(fileItem.getInputStream().available() <= 0)
                        continue;
                    String fileName = fileItem.getName();
                    String fileExt = fileName.substring(fileName.lastIndexOf("."));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
                    String str = sdf.format(new Date());
                    String destFileName = "/picture/" + str;
                    switch (fileItem.getFieldName()){
                        case "cover":
                            destFileName += "0" + fileExt;
                            goods.setCover(destFileName);
                            break;
                        case "image1":
                            destFileName += "1" + fileExt;
                            goods.setImage1(destFileName);
                            break;
                        case "image2":
                            destFileName += "2" + fileExt;
                            goods.setImage2(destFileName);
                            break;
                    }
                    String serverDestFileName = request.getServletContext().getRealPath(destFileName);
                    InputStream is = fileItem.getInputStream();
                    File file = new File(serverDestFileName);
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        fos.write(buffer);
                    }
                    fos.close();
                    is.close();
                }
            }
            String s = gService.update(goods);
            request.setAttribute("g", goods);
            if("0".equals(s)){
                request.setAttribute("msg", "修改成功");
                request.setAttribute("pageNumber", String.valueOf(pageNumber));
                request.getRequestDispatcher("/admin/goods_list?pageNumber="+pageNumber+"&type="+type).forward(request, response);
                return;
            }
            if(s.contains("1")){
                request.setAttribute("failMsg", "价格必须大于零");
                request.getRequestDispatcher("/admin/goods_editshow").forward(request, response);
            }
            if(s.contains("2")){
                request.setAttribute("failMsg", "库存必须大于等于零");
                request.getRequestDispatcher("/admin/goods_editshow").forward(request, response);
            }
        } catch (FileUploadException | SQLException e) {
            e.printStackTrace();
        }
    }

}
