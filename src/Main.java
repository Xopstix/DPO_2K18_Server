import config.Config;
import config.ObjectFile;
import controller.ServerController;
import network.Server;
import utility.ConectorDB;
import views.UsersView;
import views.VistaEvolucio;
import views.VistaPrincipal;
import views.VistaTop;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.AttributedCharacterIterator;

/**
 * Created by xavipargela on 12/3/18.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Config data;
        ObjectFile objData = new ObjectFile();

        Graphics g = new Graphics() {
            @Override
            public Graphics create() {
                return null;
            }

            @Override
            public void translate(int x, int y) {

            }

            @Override
            public Color getColor() {
                return null;
            }

            @Override
            public void setColor(Color c) {

            }

            @Override
            public void setPaintMode() {

            }

            @Override
            public void setXORMode(Color c1) {

            }

            @Override
            public Font getFont() {
                return null;
            }

            @Override
            public void setFont(Font font) {

            }

            @Override
            public FontMetrics getFontMetrics(Font f) {
                return null;
            }

            @Override
            public Rectangle getClipBounds() {
                return null;
            }

            @Override
            public void clipRect(int x, int y, int width, int height) {

            }

            @Override
            public void setClip(int x, int y, int width, int height) {

            }

            @Override
            public Shape getClip() {
                return null;
            }

            @Override
            public void setClip(Shape clip) {

            }

            @Override
            public void copyArea(int x, int y, int width, int height, int dx, int dy) {

            }

            @Override
            public void drawLine(int x1, int y1, int x2, int y2) {

            }

            @Override
            public void fillRect(int x, int y, int width, int height) {

            }

            @Override
            public void clearRect(int x, int y, int width, int height) {

            }

            @Override
            public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void drawOval(int x, int y, int width, int height) {

            }

            @Override
            public void fillOval(int x, int y, int width, int height) {

            }

            @Override
            public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawString(String str, int x, int y) {

            }

            @Override
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {

            }

            @Override
            public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public void dispose() {

            }
        };

        data = objData.readData();

        VistaPrincipal vistaPrincipal = null;
        VistaEvolucio vistaEvolucio = null;
        VistaTop vistaTop = null;
        UsersView vistaUsers = null;
        //Gestion BBDD
        ResultSet prueba;

        //Login BBDD
        //ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
        ConectorDB conn = new ConectorDB(data.getDbUser(), data.getDbPassword(), data.getDbName(), data.getDbPort());
        conn.connect();

        prueba = conn.selectQuery("SELECT * FROM Usuario");

        try {
            //Recorremos toda la tabla de usuarios de la BBDD.
            while (prueba.next())
            {
                //Especificamente le ponenmos que campos queremos leer de la BBDD
                System.out.println (prueba.getObject("username") + " " + prueba.getObject("contrasena"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problema al recuperar les dades...");
        }
        //Desconexion BBDD
        //conn.disconnect();

        try{

            vistaPrincipal = new VistaPrincipal();
            vistaEvolucio = new VistaEvolucio();
            vistaTop = new VistaTop();
            vistaUsers = new UsersView();

        }catch (IOException e){

            e.printStackTrace();
        }

        Server server = new Server(conn);

        ServerController serverController = new ServerController(vistaPrincipal, vistaEvolucio, vistaTop, vistaUsers, conn);

        vistaPrincipal.registrarControladorBoton(serverController);
        vistaEvolucio.registrarControladorBoton(serverController);
        vistaTop.registrarControladorBoton(serverController);
        vistaUsers.registrarControladorBoton(serverController);
        vistaPrincipal.setVisible(true);

        server.run();

    }
}
