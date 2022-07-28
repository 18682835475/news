package cn.news.utils;

import org.testng.annotations.Test;

import java.io.File;
import java.util.UUID;

/**
 * 文件路径加密工具类
 * 1。用户自定义选择文件 --原始文件名
 * 2.通过UUID拼接产生新的服务器名 -- UUID_原始文件名
 * 3.将文件分割到不同的文件夹中 -- hash 通过服务器名生成对应的文件路径
 * 4.通过服务器名，将其存放的文件路径再次解析出来
 * 5。最后通过服务器(Tomcat) 部署的位置再次将图片显示在网页中
 * @author LCB
 * @date 2022/7/4 14:11
 */
public class FilePathUtils {
    @Test
    public void test(){
        System.out.println("test");
        System.out.println("UUID:"+ UUID.randomUUID()+"_qq.png"); //唯一值UUID
        System.out.println(System.currentTimeMillis()+"_qq.png"); //当前时间戳
    }
    @Test
    public void test01(){
        System.out.println("test01");
        System.out.println(Integer.toHexString(123123));
        String name = "qq.png";
        int hashcode = name.hashCode();
        int dir1 = hashcode&0xf;  // 0--15  通过文件名计算路径
        int dir2 = (hashcode&0xf0)>>4;  //0--15  通过文件名计算路径
        System.out.println(dir1+"\\"+dir2);
    }

    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
    public static String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * @Method: makePath
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     */
    public static String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

    @Test
    public void test3(){
        String filename = "qq.png";
        String savePath =  makePath(filename,"E://");
        System.out.println(savePath);
        String saveName = makeFileName(filename);
        System.out.println(saveName);
    }

    /**
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     * @param filename 要下载的文件名
     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     * @return 要下载的文件的存储目录
     */
    public static String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
