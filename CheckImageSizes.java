import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CheckImageSizes {
    public static void main(String[] args) throws Exception {
        String[] files = {
            "assets/Enchantress/Idle.png",
            "assets/Enchantress/Walk.png",
            "assets/Enchantress/Attack_1.png",
            "assets/Enchantress/Special_Attack_1.png",
            "assets/Enchantress/Special_Attack_2.png",
            "assets/Enchantress/Special_Attack_3.png",
            "assets/Enchantress/Roll.png",
            "assets/Enchantress/Take.png"
        };
        
        for (String file : files) {
            BufferedImage img = ImageIO.read(new File(file));
            System.out.println(new File(file).getName() + ": " + img.getWidth() + "x" + img.getHeight());
        }
    }
}
