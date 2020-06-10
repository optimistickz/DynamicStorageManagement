import javax.swing.*;
import java.awt.*;

/**
 * created by kz on
 */
public class Input extends JPanel{
    JPanel inputPanel;
    static Task task=new Task("add",Task.nowNumber+1,0,200,50) ;
    public Input()
    {
        //输入指令界面
        GridLayout layout=new GridLayout(5,3);
        inputPanel=new JPanel(layout);
        JButton btn1=new JButton("1");
        JButton btn2=new JButton("2");
        JButton btn3=new JButton("3");
        JButton btn4=new JButton("4");
        JButton btn5=new JButton("5");
        JButton btn6=new JButton("6");
        JButton btn7=new JButton("7");
        JButton btn8=new JButton("8");
        JButton btn9=new JButton("9");
        JButton delBtn=new JButton("删除");
        JButton btn0=new JButton("0");
        JButton ensureBtn=new JButton("确认");
        JButton releaseBtn=new JButton("释放");
        JButton addBtn=new JButton("申请");

        //将数字按键添加到组件中
        inputPanel.add(releaseBtn);
        inputPanel.add(addBtn);
        inputPanel.add(task.taskLabel);
        inputPanel.add(btn1);
        inputPanel.add(btn2);
        inputPanel.add(btn3);
        inputPanel.add(btn4);
        inputPanel.add(btn5);
        inputPanel.add(btn6);
        inputPanel.add(btn7);
        inputPanel.add(btn8);
        inputPanel.add(btn9);
        inputPanel.add(delBtn);
        inputPanel.add(btn0);
        inputPanel.add(ensureBtn);

        //按钮监听器
        btn0.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"0");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"0");
        });

        btn1.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"1");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"1");
        });

        btn2.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"2");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"2");
        });

        btn3.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"3");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"3");
        });

        btn4.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"4");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"4");
        });

        btn5.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"5");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"5");
        });

        btn6.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"6");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"6");
        });

        btn7.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"7");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"7");
        });

        btn8.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"8");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"8");
        });

        btn9.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
                task.setTaskSize(task.taskSize+"9");
            else if(task.operation.equals("release"))
                task.setSerialNumber(task.serialNumber+"9");
        });

        releaseBtn.addActionListener(actionEvent -> {
            task.setOperation("release");
            task.setSerialNumber("0");
            task.setTaskSize("0");
            });

        addBtn.addActionListener(actionEvent -> {
            task.setOperation("add");
            task.setTaskSize("0");
            task.taskLabel.setBackground(Color.LIGHT_GRAY);
            task.setSerialNumber(""+(Task.nowNumber+1));
        });

        delBtn.addActionListener(actionEvent -> {
            if(task.operation.equals("add"))
            {
                String temp= ((Integer) task.taskSize).toString();
                if(temp.length()<=1)
                    task.setTaskSize("0");
                else
                    task.setTaskSize(temp.substring(0,temp.length()-1));
            }
            else if(task.operation.equals("release"))
            {
                String temp= ((Integer) task.serialNumber).toString();
                if(temp.length()<=1)
                    task.setSerialNumber("0");
                else
                    task.setSerialNumber(temp.substring(0,temp.length()-1));
            }
        });

        ensureBtn.addActionListener(actionEvent -> {
            Task temp = new Task(task.operation,task.serialNumber,task.taskSize,200,50);
            if(task.operation.equals("add"))
                temp.taskLabel.setBackground(Color.BLUE);
            else
                temp.taskLabel.setBackground(Color.GREEN);
            temp.taskLabel.setOpaque(true);
            temp.taskLabel.setFont(new Font("微软雅黑",Font.BOLD,16));
            temp.taskLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
            Initializer.instructView.add(temp);
            //任务编号+1
            if(task.operation.equals("add"))
            {
                Task.nowNumber++;
                task.setSerialNumber(""+(Task.nowNumber+1));
            }
            else if(task.operation.equals("release"))
                task.setSerialNumber("0");
            task.setTaskSize("0");
        });

        inputPanel.setBounds(310,400,420,240);
    }
    public void clear()
    {
        Input.task.setSerialNumber("1");
        Task.nowNumber=0;
        Input.task.setTaskSize("0");
        Input.task.setOperation("add");
        Input.task.taskLabel.setBackground(Color.LIGHT_GRAY);
    }
}
