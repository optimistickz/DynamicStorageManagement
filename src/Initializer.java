import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * created by kz on
 */
public class Initializer {
    static JFrame mainScreen=new JFrame("动态内存分区");
    static InstructArea instructView=new InstructArea();
    static JPanel selectDistrict=new JPanel();
    static  Input input=new Input();
    static Memory memory=new Memory();
    static JRadioButton firstFit=new JRadioButton("首次适应算法");
    static JRadioButton bestFit=new JRadioButton("最佳适应算法");
    public static void Init(){
        mainScreen.setSize(1000,700);
        mainScreen.setLocation(100,100);
        //内存显示区
        mainScreen.add(memory.memoryPanel,-1);
        memory.memoryPanel.setLayout(null);

        //指令显示区
        mainScreen.add(instructView.scrollPane,-1);

        //选择算法单选按钮
        ButtonGroup algorithm=new ButtonGroup();
        firstFit.setFont(new Font("微软雅黑",Font.BOLD,18));
        bestFit.setFont(new Font("微软雅黑",Font.BOLD,18));
        algorithm.add(firstFit);
        algorithm.add(bestFit);
        firstFit.setSelected(true);
        selectDistrict.add(firstFit);
        selectDistrict.add(bestFit);
        selectDistrict.setBounds(420,60,200,80);
        mainScreen.add(selectDistrict);

        //加入输入指令界面
        mainScreen.add(input.inputPanel);

        //指令执行界面
        JButton singleBtn=new JButton("单步执行");
        JButton serialBtn=new JButton("连续执行");
        singleBtn.setFont(new Font("微软雅黑",Font.BOLD,18));
        serialBtn.setFont(new Font("微软雅黑",Font.BOLD,18));
        mainScreen.add(singleBtn);
        mainScreen.add(serialBtn);
        singleBtn.setBounds(320,240,160,60);
        serialBtn.setBounds(500,240,160,60);

        //单步执行
        singleBtn.addActionListener(actionEvent -> {
            Initializer.instructView.executeInstruct();
            //输入提示
            boolean isExist=false;
            for (StorageBlock storageBlock : Initializer.memory.filledList)
                if (storageBlock.serialNumber == Input.task.serialNumber) {
                    Input.task.taskLabel.setBackground(Color.LIGHT_GRAY);
                    Input.task.setTaskSize("" + storageBlock.size);
                    isExist = true;
                    break;
                }
            for (Task task : Initializer.instructView.instructQue)
                if (task.serialNumber == Input.task.serialNumber&&task.operation.equals("add")) {
                    Input.task.taskLabel.setBackground(Color.LIGHT_GRAY);
                    Input.task.setTaskSize("" + task.taskSize);
                    isExist = true;
                    break;
                }
            if(!isExist&&Input.task.operation.equals("release"))
                Input.task.taskLabel.setBackground(Color.RED);
        });

        //清空按钮
        JButton clearBTN=new JButton("清空界面");
        clearBTN.setFont(new Font("微软雅黑",Font.BOLD,18));
        mainScreen.add(clearBTN);
        clearBTN.setBounds(420,180,160,50);
        clearBTN.setBackground(Color.RED);

        clearBTN.addActionListener(actionEvent -> {
            memory.clear();
            instructView.instructArea.removeAll();
            instructView.instructQue.clear();
            input.clear();
        });

        //连续执行
        serialBtn.addActionListener(actionEvent -> {
            Timer timer = new Timer(true);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(instructView.instructQue.size()!=0)
                    {
                        Initializer.instructView.executeInstruct();
                    }
                    else {
                        this.cancel();
                        timer.cancel();
                    }
                }
            };
            timer.schedule(timerTask, 0, 1000);
        });

        //主界面设置
        mainScreen.getContentPane().setLayout(null);
        mainScreen.getContentPane().setBackground(Color.WHITE);
        mainScreen.setVisible(true);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
