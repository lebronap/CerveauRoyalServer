package servlet;

import Model.Constant;
import SMA.ProcessBehaviour;
import jade.core.behaviours.Behaviour;
import jade.wrapper.gateway.JadeGateway;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.logging.Logger;

public class SMAServlet  extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
//		super.doDelete(req, resp);
        String url = request.getRequestURI();

        String JSON = request.getParameter("JSON");

        Logger logger = Logger.getLogger(SMAServlet.class.getName());
        logger.warning(url);
        logger.warning("This is a warning!");
        logger.warning(JSON);


        int lastIndex = url.lastIndexOf("/");
        String urlREST = url.substring(lastIndex + 1);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        switch(urlREST){
            case("user"):
                System.out.println("get /user request");
                ProcessBehaviour behaviour = new ProcessBehaviour(JSON, Constant.USER_INFO_NAME, Constant.SMA_GET);

                activeAgent(behaviour);
                out.println(behaviour.answer);
                break;
        }

//        System.out.println("处理Get请求。。。");
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = response.getWriter();
//
//        ProcessBehaviour behaviour = new ProcessBehaviour("testAgent", "this is content");
//        activeAgent(behaviour);
//
//        out.println(behaviour.answer);

        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
//		super.doGet(request, response);
        String url = request.getRequestURI();
        int lastIndex = url.lastIndexOf("/");
        String urlREST = url.substring(lastIndex + 1);
        PrintWriter out = response.getWriter();

        switch (urlREST){
            case("user"):
                System.out.println("处理Post请求。。。");
//                response.setContentType("text/html;charset=utf-8");

                out.println("<strong>Hello servlet</strong>");
                break;
        }

        out.flush();
        out.close();
    }

    private void activeAgent(Behaviour behaviour) {
        try {
            JadeGateway.execute(behaviour);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
