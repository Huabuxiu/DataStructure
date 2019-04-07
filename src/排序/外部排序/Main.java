package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // 存放测试和结果文件路径，改变环境修改此地址
    private final static String ROOT_FILE_PATH = "C:\\Users\\花不休\\code\\JAVA\\homework";
    // 测试文件，创建文件时初始化
    private static String[] genFiles = new String[10];
    // 测试切分大文件的1MB,
    // 测试切分文件大小5mb
    private final static int SIZE = 1024*1;
    // 切分文件大小/
    private final static int BYTE_SIZE = SIZE * 1024;
    // 线程任务完成计数器
    private static CountDownLatch doneSignal;
    // 所有的切分文件
    private static final List<File> divFiles = new ArrayList<>();

    public static void main(String[] args) {
        Long root = System.currentTimeMillis();

        //生成测试数据
        generateTestFiles();

        Long gen = System.currentTimeMillis();
        System.out.println("\n***************");
        System.out.println(String.format("初始化数据完成:%s s。", (gen - root) / 1000));
        System.out.println("***************");


        Long divStart = System.currentTimeMillis();

        //分割文件并进行内部排序
        divisionAndSortFiles();

        Long divEnd = System.currentTimeMillis();
        System.out.println("\n***************");
        System.out.println(String.format("切分数据完成:%s s。", (divEnd - divStart) / 1000));
        System.out.println("***************");


        Long mergeStart = System.currentTimeMillis();
        System.out.println("\n***************");
        //合并小文件，进行归并
        try {
            merge();
        } catch (IOException e) {
           //
        }
        Long mergeEnd = System.currentTimeMillis();
        System.out.println(String.format("合并完成:%s 秒。", (mergeEnd - mergeStart) / 1000));
        System.out.println("\n***************");

    }

    public static void  merge() throws IOException {
        List<File> divFileList = new ArrayList<>(divFiles);
        divFiles.clear();



        //分隔后小文件读取类,归并链表
        List<FileEntity> bwList = new ArrayList<FileEntity>();

        for (int i=0; i < divFileList.size();i++ ){
            //创建每个小文件的读取类
            FileEntity fe = new FileEntity(
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(divFileList.get(i)))));
            //加入链表
            bwList.add(fe);
        }

        //结果集的写入缓冲区
        BufferedWriter resultBw = null;
        int filecount=1;
        int linecount=0;
        String fileName =ROOT_FILE_PATH + "/resultFile"+filecount+".txt";
        try{

            resultBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            int count=0;
            FileEntity fe = null;   //排好序的一行
            StringBuilder sb = new StringBuilder(); //结果集
            while ((fe = getFirstFileEntity(bwList)) != null){  //进行归并排序

                linecount++;
                //每一百万划分一个子文件 测试
                //测试一千万划分一个子文件
                if (linecount == 10000*1000+1)
                {
                    //写入当前缓冲区
                    if (sb.length()>0) {
                        resultBw.write(sb.toString());
                        sb.setLength(0);
                        resultBw.flush();
                    }
                    //
                    System.out.println(String.format("结果文件：%d 生成",filecount));
                    filecount++;
                    linecount = 1;
                    fileName =ROOT_FILE_PATH + "/resultFile"+filecount+".txt";
                    resultBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                }
                //计数缓冲区
                count++;
                //往结果集中写下一行
                sb.append(fe.getLine()+"\n");
                //往后读一行
                fe.nextLine();
                //清除缓冲区
                if ((count + 1) % (10000) == 0) {
                    resultBw.write(sb.toString());
                    sb.setLength(0);
                    resultBw.flush();
                }
            }
            if (sb.length()>0){
                resultBw.write(sb.toString());
                resultBw.flush();
                sb = null;
            }
        }catch (Exception e){
            //no-op
        }finally {
            if (resultBw != null) {
                try {
                    resultBw.flush();
                    resultBw.close();
                } catch (IOException e) {
                    // no-op
                }
            }
        }
    }


    /**
    * @Description:  按顺序读取所有分隔文件
    * @Param:
    * @return:
    * @Author: Huabuxiu
    * @Date: 2019/4/4
    */
    public static FileEntity getFirstFileEntity(List<FileEntity> bwList){
        if (bwList.size() == 0) {
            return null;
        }
        //读取每一个分隔文件
        Iterator<FileEntity> it = bwList.iterator();
        while (it.hasNext()) {
            FileEntity fe = it.next();
            // 如果文件读到完就关闭流和删除在集合的文件流
            if (fe.getLine() == null) {
                fe.close();
                it.remove();
            }
        }
        // 获取最小的一行数据，进行排序
        return doSort(bwList);
    }



    public static FileEntity doSort(List<FileEntity> bwList){

        FileEntity minfe = bwList.get(0);
        for (int i=1; i< bwList.size();i++){
            if (minfe.getId() > bwList.get(i).getId()){
                minfe = bwList.get(i);
            }
        }
        return minfe;
    }


    /*
     * 分隔和排序文件
     */
    public static void divisionAndSortFiles(){
        for (int i =0; i < 10; i++) {
            //分割每一个文件
            divWorkOut(genFiles[i],i);
        }
    }



        /** 
        * @Description: 分割整个文件 
        * @Param:  
        * @return:  
        * @Author: Huabuxiu 
        * @Date: 2019/4/3 
        */ 
    public static void divWorkOut(String filePath,int i){
        try {

            List<File> lists = divWork(filePath);
            // 将分割完成的文件对象存入
            divFiles.addAll(lists);
            System.out.println("完成分割任务："+i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    /**
    * @Description: 把文件切分
    * @Param:
    * @return: 返会切分好的文件列表
     * @Author: Huabuxiu
    * @Date: 2019/4/3
    */

    public static List<File> divWork(String filePath) throws IOException{
        File file= new File(filePath);
        if (!file.exists())
            throw new Error("文件不存在");

        // 得到文件大小k
        int mbsize = (int) Math.ceil(file.length() / 1024);
        // 计算得到切分的文件数
        int fileNum = (int) Math.ceil(mbsize / SIZE) + 1;
        // 创建临时文件
        List<File> tempFileList = createTempFileList(file, fileNum);
        // 进行内部排序并写入文件
        divAndSort(file, tempFileList);
        return tempFileList;
    }


    /**
    * @Description:  读取大文件并写入切分好的小文件中去
    * @Param:   原始的大文件，生成好的临时切分文件列表
    * @return:
    * @Author: Huabuxiu
    * @Date: 2019/4/3
    */
    private static void divAndSort(File file, List<File> tempFileList) {
        BufferedReader bRead = null;
        try {
            // 读取大文件
            bRead = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            // 行数据保存对象
            String line = null;
            // 临时文件索引
            int index = tempFileList.size() - 1;
            // 保存数据
            List<String> lineList = new ArrayList<>();
            // 统计文件大小
            int byteSum = 0;
            // 循环临时文件并循环大文件
            while ((line = bRead.readLine()) != null) {
                line += "\n";
                byteSum += line.getBytes().length;
                // 如果长度达到每个文件大小则重新计算
                if (byteSum >= BYTE_SIZE) {
                    Long time0 = System.currentTimeMillis();
                    // 写入到文件
                    putLineListToFile(tempFileList.get(index), lineList);
                    Long time1 = System.currentTimeMillis();
//                    System.out.println(String.format("写入文件%s：%s ms", index, time1 - time0));
                    index--;
                    byteSum = line.getBytes().length;
                    lineList.clear();
                }
                lineList.add(line);
            }
            if (lineList.size() > 0) {
                // 写入到文件
                putLineListToFile(tempFileList.get(0), lineList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bRead != null) {
                    bRead.close();
                }
            } catch (IOException e) {
                // no-op
            }
        }
    }


    /**
    * @Description: 对小文件进行内部排序并存入磁盘
    * @Param:  对应的小文件对象，数据
    * @return:
    * @Author: Huabuxiu
    * @Date: 2019/4/3
    */

    private static void putLineListToFile(File file, List<String> lineList) throws IOException {
        FileOutputStream tempFileFos = null;
        try {
            // 第一次写入文件时，调用Collection.sort进行内部排序
            lineList.sort(new LineComparator());
            tempFileFos = new FileOutputStream(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lineList.size(); i++) {
                sb.append(lineList.get(i));
                if ((i + 1) % 1000 == 0) {
                    tempFileFos.write(sb.toString().getBytes());
                    sb.setLength(0);
                }
            }
            if (sb.length() > 0) {
                tempFileFos.write(sb.toString().getBytes());
            }
            sb = null;
        } finally {
            if (tempFileFos != null) {
                tempFileFos.close();
            }
        }
    }



    /**
    * @Description: 生成临时文件
    * @Param:  需要切分的大文件，和切分成小文件的数目
    * @return:
    * @Author: Huabuxiu
    * @Date: 2019/4/3
    */

    private static List<File> createTempFileList(File file, int fileNum) {
        List<File> tempFileList = new ArrayList<File>();
        //获取路径
        String fileFolder = file.getParent();
        String name = file.getName();
        for (int i = 0; i < fileNum; i++) {
            // 创建临时文件
            File tempFile = new File(fileFolder + "/" + name + ".temp_" + i + ".txt");
            // 如果存在就删除
            if (tempFile.exists()) {
                tempFile.delete();
            }
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFileList.add(tempFile);
        }
        return tempFileList;
    }





    /**
     * 产生测试文件
     */
    public static void generateTestFiles() {
        // 启用十个线程生成测试文件
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        doneSignal = new CountDownLatch(10);
        for (int i = 0; i < genFiles.length; i++) {
            genFiles[i] = ROOT_FILE_PATH + "/Data" + i + ".txt";
            executorService.submit(new generateFileTask(genFiles[i]));
        }
        try {
            // 等待生成文件
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }



    static class generateFileTask implements Runnable {
        final String filePath;

        public generateFileTask(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public void run() {
            try {
                generateTestFile(filePath);
                // 任务执行完毕递减锁存器
                doneSignal.countDown();
                System.out.print("生成文件：" + doneSignal.getCount() + "，  ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 创建文件
     *
     * @param filePath
     * @throws Exception
     */
    public static void generateTestFile(String filePath) throws Exception {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        FileChannel channel = null;
        RandomAccessFile rf = null; //随机读取文件
        try {
            File testFile = new File(filePath);
            if (testFile.exists())
                testFile.delete();
            // 如果文件不存在则创建
            testFile.createNewFile();

            rf = new RandomAccessFile(testFile, "rw");
            channel = rf.getChannel();
            int capacity = 1024 * 1024; // 字节
            ByteBuffer writerBuffer = ByteBuffer.allocate(capacity);

            //测试一百万数据
            // 一千万
            for (long i = 0; i < 10000 * 1000; i++) {
                sb.append(random.nextInt(100000)).append("\n");
                // 刷新缓冲
                if ((i + 1) % 10000 == 0) {
                    writerBuffer.put(sb.toString().getBytes());
                    sb.setLength(0);
                    writerBuffer.flip();
                    channel.write(writerBuffer);
                    writerBuffer.clear();
                }
            }
            if (sb.length() > 0) {
                writerBuffer.put(sb.toString().getBytes());
                writerBuffer.flip();
                channel.write(writerBuffer);
                writerBuffer.clear();
            }
        } catch (IOException e) {
            System.out.println("生成测试文件失败！" + e.getMessage());
            throw e;
        } finally {
            try {
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                // no-op
            }
            try {
                if (channel.isOpen()) {
                    channel.close();
                }
            } catch (IOException e) {
                // no-op
            }

        }
    }

}
