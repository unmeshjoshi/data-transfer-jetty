package com.apisecurity;

import com.apisecurity.auth.ClientKey;
import com.apisecurity.auth.HMACAuthorizationHeader;
import com.apisecurity.auth.validators.HMACSignatureValidator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JettyServer {

    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(HelloServlet.class, "/*");
        server.start();
        server.join();
    }

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                IOException
        {

            HMACAuthorizationHeader from = HMACAuthorizationHeader.createFrom(request);
            boolean validator = new HMACSignatureValidator().isValid(ClientKey.valueOf("clientKeyId", "clientKey"), from.getSignature(), request);
            if(!validator) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().print("Invalid Client");
                return;
            }
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }
}