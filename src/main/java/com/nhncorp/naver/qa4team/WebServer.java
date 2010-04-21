package com.nhncorp.naver.qa4team;

import java.io.File;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import de.michaeltamm.fightinglayoutbugs.SocketHelper;

public class WebServer {
    private Server _server;
    private int _port;

    public WebServer() {
        _server = new Server();
        File webappDir = new File("web-inf");
        WebAppContext webAppContext = new WebAppContext(webappDir.getAbsolutePath(), "/");
        _server.addHandler(webAppContext);
    }

    public void start() {
        _port = SocketHelper.findFreePort();
        Connector connector = new SocketConnector();
        connector.setPort(_port);
        _server.addConnector(connector);
        try {
            _server.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start " + _server, e);
        }
    }

    public void stop() {
        try {
            _server.stop();
        } catch (Exception e) {
            System.err.print("Could not stop " + _server + ": ");
            e.printStackTrace(System.err);
        }
    }

    public int getPort() {
        return _port;
    }
}