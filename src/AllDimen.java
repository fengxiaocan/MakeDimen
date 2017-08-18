import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class AllDimen {
	private final static String rootPath = "DimenValues/values-{0}x{1}";
	private final static int width = 720;
	private final static int height = 1280;
	private final static float dw = 720f;
	private final static float dh = 1280f;

	private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
	private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

	public static void main(String[] args) {
//		makeString(320, 480);
//		makeString(480, 800);
//		makeString(480, 854);
//		makeString(540, 960);
//		makeString(600, 1024);
//		makeString(720, 1184);
//		makeString(720, 1196);
//		makeString(720, 1280);
//		makeString(768, 1024);
//		makeString(800, 1280);
//		makeString(1080, 1812);
//		makeString(1080, 1920);
//		makeString(1440, 2560);
//		makeString(3200, 4500);
		makeString(480, 854);
	}

	public static void makeString(int w, int h) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<resources>");
		float cellw = w / dw;
		for (int i = 1; i < width; i++) {
			sb.append(WTemplate.replace("{0}", i + "").replace("{1}", change(cellw * i) + ""));
		}
		sb.append(WTemplate.replace("{0}", width + "").replace("{1}", w + ""));
		sb.append("</resources>");

		StringBuffer sb2 = new StringBuffer();
		sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb2.append("<resources>");
		float cellh = h / dh;
		for (int i = 1; i < height; i++) {
			sb2.append(HTemplate.replace("{0}", i + "").replace("{1}", change(cellh * i) + ""));
		}
		sb2.append(HTemplate.replace("{0}", height + "").replace("{1}", h + ""));
		sb2.append("</resources>");

		String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
		File rootFile = new File(path);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}
		File layxFile = new File(path, "lay_x.xml");
		File layyFile = new File(path, "lay_y.xml");
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
			pw.print(sb.toString());
			pw.close();
			pw = new PrintWriter(new FileOutputStream(layyFile));
			pw.print(sb2.toString());
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static float change(float a) {
		int temp = (int) (a * 100);
		return temp / 100f;
	}
}