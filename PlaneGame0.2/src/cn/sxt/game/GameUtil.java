package cn.sxt.game;
 
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
 
/**
 * 工具类:返回指定路径文件的图片对象
 * @author 陈洪洋
 *
 */
public class GameUtil {
    // 工具类最好将构造器私有化  这样就不可以被实例化了
    private GameUtil() {
    	
    } 
    
    /**
     * 返回指定路径文件的图片对象
     * @param path
     * @return
     */
    public static Image getImage(String path) {
        BufferedImage bufferedImage = null;
        try {
            URL url = GameUtil.class.getClassLoader().getResource(path);
            bufferedImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
}