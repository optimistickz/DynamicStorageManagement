import javax.swing.*;
import java.awt.*;

/**
 * created by kz on
 */
public class StorageBlock {
    public JLabel block=new JLabel();
    int start;                 //内存块起始位置
    int end;                 //内存块结束位置
    int size;                //内存块大小
    int serialNumber=-1;      //内存块中任务序号
    StorageBlock(Task task)
    {
        size=task.taskSize;
        String str="作业"+task.serialNumber+" "+size+"k";
        serialNumber=task.serialNumber;
        block.setFont(new Font("微软雅黑",Font.BOLD,18));
        block.setText(str);
        block.setOpaque(true);
        block.setBackground(Color.CYAN);
        block.setSize(250,size);
        block.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        block.setHorizontalAlignment(0);
    }
    StorageBlock(int start,int end,int size)
    {
        this.size=size;
        this.start=start;
        this.end=end;
        block.setFont(new Font("微软雅黑",Font.BOLD,18));
        block.setText("空闲 "+size+"k");
        block.setOpaque(true);
        block.setSize(250,size);
        block.setBackground(Color.ORANGE);
        block.setBorder(BorderFactory.createLineBorder(Color.BLUE,1));
        block.setHorizontalAlignment(0);
    }

    //刷新内存区空闲块显示
    public void reNewEmptyBlock()
    {
        block.setSize(250,size);
        block.setLocation(0,start);
        block.setText("空闲 "+size+"k");
        block.repaint();
    }

    //绘制起始结束位置标记
    public void paintStartEnd()
    {
        block.removeAll();
        JLabel start=new JLabel(""+this.start);
        JLabel end=new JLabel(""+this.end);
        this.block.add(start);
        this.block.add(end);
        start.setFont(new Font("微软雅黑",Font.BOLD,12));
        end.setFont(new Font("微软雅黑",Font.BOLD,12));
        start.setHorizontalAlignment(0);
        end.setHorizontalAlignment(0);
        start.setBounds(5,0,30,12);
        end.setBounds(5,size-12,30,12);
        System.out.println(start.getText());
    }
}
