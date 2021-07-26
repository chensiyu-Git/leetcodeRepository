import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class test {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        //假定程序每个执行耗时10ms
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        CompletionService<Integer> service = new ExecutorCompletionService<Integer>(executorService);
        List<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            //**********************************
            int finalI = i;
            futureList.add(service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName()+":"+finalI);
                    return finalI;
                }
            }));
            //************************************
//            try {
//                Thread.sleep(10);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            list.add(i);
        }
        long cost = System.currentTimeMillis()-start;
        System.out.println("cost:"+cost);
//            printFutureList(futureList);
        printServer(service,futureList.size());
//        System.out.println(futureList.size());
//        System.out.println(list.size());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Model model = new Model();
        Calendar c = Calendar.getInstance();
        model.setInnerDate(c.getTime());
        System.out.println(format.format(c.getTime()));
        System.out.println(format.format(model.getInnerDate()));
        System.out.println(monthChange(c));
        System.out.println(format.format(c.getTime()));
        System.out.println(format.format(model.getInnerDate()));
    }

    private static boolean monthChange(Calendar c) {
        int month = c.get(Calendar.MONTH);
        //预约最大可预约7天，所以一个月的前7天要去查头一个月的数据
        c.add(Calendar.DATE,-7);
        return month != c.get(Calendar.MONTH);
    }

    public static void printFutureList(List<Future<Integer>> list) throws InterruptedException, ExecutionException, TimeoutException {
        StringBuffer sbf = new StringBuffer("[");
        int count = 0;
        for (Future<Integer> fi : list){
            sbf.append(fi.get(3,TimeUnit.MILLISECONDS)).append(",");
        }
        sbf.deleteCharAt(sbf.length()-1);
        sbf.append("]");
        System.out.println(sbf.toString());
    }

    public static void printServer( CompletionService<Integer> service,int size) throws InterruptedException, TimeoutException, ExecutionException {
        StringBuffer sbf = new StringBuffer("[");
        for (int i=0;i<size;i++){
            Integer ii = service.take().get(1,TimeUnit.MILLISECONDS);
            sbf.append(ii).append(",");
        }
        sbf.deleteCharAt(sbf.length()-1);
        sbf.append("]");
        System.out.println(sbf.toString());
    }

    public static void printList(List<Integer> list){
        StringBuffer sbf = new StringBuffer("[");
        for (Integer i : list){
            sbf.append(i).append(",");
        }
        sbf.deleteCharAt(sbf.length()-1);
        sbf.append("]");
        System.out.println(sbf.toString());
    }

    public static class Model{
        Date innerDate;

        public Date getInnerDate() {
            return innerDate;
        }

        public void setInnerDate(Date innerDate) {
            this.innerDate = innerDate;
        }
    }
}
