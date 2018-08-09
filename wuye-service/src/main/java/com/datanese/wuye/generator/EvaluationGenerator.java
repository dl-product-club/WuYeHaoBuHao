package com.datanese.wuye.generator;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectResult;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.rest.ImageController;
import com.datanese.wuye.util.SnowflakeIdWorker;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvaluationGenerator {
    //INSERT INTO `evaluation` VALUES(6412042761523953699, 1, 6411869848636227584, 1, '今天物业帮我收衣服了', '2018-06-29 10:32:25', 'image1,image2')
    private static String GEN_SQL_TEMPLATE = "INSERT INTO `evaluation` VALUES(#{id}, #{communityId}, #{userId}, #{rate}, '#{comment}', '#{datetime}', '#{imageUrls}')";

    private static int COMMUNITY_ID = 1;
    private static int NUMBER = 200;

    private List<String> imageList = new ArrayList();
    private List<String> userList = new ArrayList();
    private List<Evaluation> evaluationList = new ArrayList();


    public void generateSQL() {
        init();

        LocalDateTime time= LocalDateTime.now();
        for (int i= 0;i<NUMBER;i++){
            time=time.plusMinutes(1);
            long id=SnowflakeIdWorker.nextId();
            String user=getRandomUser();
            String image=getRandomImage();
            Evaluation evaluation=getRandomEvaluation();
            String result=GEN_SQL_TEMPLATE;
            result=result.replace("#{id}",id+"");
            result=result.replace("#{communityId}",COMMUNITY_ID+"");
            result=result.replace("#{userId}",user);
            result=result.replace("#{rate}",evaluation.rate);
            result=result.replace("#{comment}",evaluation.comments);
            result=result.replace("#{datetime}",time.toString());
            result=result.replace("#{imageUrls}",image);
            System.out.println(result+";");
        }
    }

    private String getRandomImage() {
        Random random = new Random();
        //取随机数  0，1，2
        int imageNumber=random.nextInt(3);
        StringBuilder sb=new StringBuilder();

        int imageSize=imageList.size();
        for (int i=0;i<imageNumber;i++){
            String path=imageList.get(random.nextInt(imageSize));
            sb.append(path).append(",");
        }
        if(sb.length()!=0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    private Evaluation getRandomEvaluation() {
        int size=evaluationList.size();
        Random random = new Random();
        return evaluationList.get(random.nextInt(size));
    }

    private String getRandomUser() {
        int size=userList.size();
        Random random = new Random();
        return userList.get(random.nextInt(size));
    }

    private void init() {
        //         endpoint: oss-cn-qingdao.aliyuncs.com
//         accessKeyId: LTAIUTqvVjBI6b3E
//         accessKeySecret: WZ1q3tAmXXV6cmzhElnULs0ene9xbe
//         bucketName: wuyehaobuhao-test
        OSSClient ossClient = new OSSClient("oss-cn-qingdao.aliyuncs.com", "LTAIUTqvVjBI6b3E", "WZ1q3tAmXXV6cmzhElnULs0ene9xbe");
        ObjectListing listing = ossClient.listObjects("wuyehaobuhao-test");
        // 遍历所有Object
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            String path = ImageController.IMAGE_PATH + objectSummary.getKey();
            if (path.endsWith("png")) {
                imageList.add(path);
            }
        }
        try {
            List<String> list=Files.readLines(new File("wuye-service/dictionary","users"), Charsets.UTF_8);
            for (String line:list) {
                userList.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        try {
            List<String> list=Files.readLines(new File("wuye-service/dictionary","comments"), Charsets.UTF_8);
            for (String line:list) {
                String[] array=line.split(";");
                Evaluation e=new Evaluation(    );
                e.comments=array[0];
                e.rate=array[1];
                evaluationList.add(e);
            }
        }catch (Exception e){
            e.printStackTrace();
            return;
        }




    }

    public static void main(String[] args) {
//        File f=new File("");
//        System.out.println(f.getAbsoluteFile());
       new EvaluationGenerator().generateSQL();
    }

    class Evaluation{
        public String comments;
        public String rate;
    }

}
