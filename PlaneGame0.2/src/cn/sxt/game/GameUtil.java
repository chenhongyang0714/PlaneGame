package cn.sxt.game;
 
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
 
/**
 * ������:����ָ��·���ļ���ͼƬ����
 * @author �º���
 *
 */
public class GameUtil {
    // ��������ý�������˽�л�  �����Ͳ����Ա�ʵ������
    private GameUtil() {
    	
    } 
    
    /**
     * ����ָ��·���ļ���ͼƬ����
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