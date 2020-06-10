import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * created by kz on
 */
public class Task {
    static public int nowNumber=0;
    int serialNumber;             //作业序列号
    String operation="add";    //操作字段，决定释放或者申请内存
    int taskSize;                //作业大小
    JLabel taskLabel=new JLabel();
    Task(String operate,int sNum,int size,int width ,int height)
    {
        operation=operate;
        taskSize=size;
        serialNumber=sNum;
        if(operate.equals("add"))
            taskLabel.setText("  作业 "+serialNumber+" 申请 "+taskSize+"k");
        else if(operate.equals("release"))
            taskLabel.setText("  作业 "+serialNumber+" 释放 "+taskSize+"k");
        taskLabel.setPreferredSize(new Dimension(width,height));
        taskLabel.setBackground(Color.LIGHT_GRAY);
        taskLabel.setOpaque(true);
    }
    Task() {
        taskLabel=new JLabel("  作业 "+serialNumber+" 申请 "+taskSize+"k");
        taskLabel.setSize(250,60);
        taskLabel.setOpaque(true);
    }

    public void setOperation(String str)
    {
        operation=str;
        if(operation.equals("add"))
            taskLabel.setText("  作业 "+serialNumber+" 申请 "+taskSize+"k");
        else if(operation.equals("release"))
            taskLabel.setText("  作业 "+serialNumber+" 释放 "+taskSize+"k");
    }

    public void setTaskSize(String str)
    {
        taskSize=Integer.parseInt(str);
        if(operation.equals("add"))
            taskLabel.setText("  作业 "+serialNumber+" 申请 "+taskSize+"k");
        else if(operation.equals("release"))
            taskLabel.setText("  作业 "+serialNumber+" 释放 "+taskSize+"k");
    }

    public void setSerialNumber(String str)
    {
        serialNumber=Integer.parseInt(str);
        if(operation.equals("add"))
            taskLabel.setText("  作业 "+serialNumber+" 申请 "+taskSize+"k");
        else if(operation.equals("release"))
        {
            taskLabel.setText("  作业 "+serialNumber+" 释放 "+taskSize+"k");
            taskLabel.setBackground(Color.RED);
            LinkedList<StorageBlock> indexList=Initializer.memory.filledList;

            //检索指令中任务是否在内存区或即将被申请，如果不在，模拟指令背景变红
            for (StorageBlock storageBlock : indexList)
                if (storageBlock.serialNumber == serialNumber) {
                    taskLabel.setBackground(Color.LIGHT_GRAY);
                    setTaskSize("" + storageBlock.size);
                    break;
                }
            for (Task task : Initializer.instructView.instructQue)
                if (task.serialNumber == Input.task.serialNumber&&task.operation.equals("add")) {
                    Input.task.taskLabel.setBackground(Color.LIGHT_GRAY);
                    Input.task.setTaskSize("" + task.taskSize);
                    break;
                }
        }
    }
}