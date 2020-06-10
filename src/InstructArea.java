import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * created by kz on
 */
public class InstructArea {
    public JPanel instructArea=new JPanel();
    public JScrollPane scrollPane=new JScrollPane(instructArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    public LinkedList<Task> instructQue= new LinkedList<>();
    InstructArea()
    {
        instructArea.setBounds(760,30,240,600);
        instructArea.setBackground(Color.LIGHT_GRAY);
        scrollPane.setBounds(760,30,220,600);
        GridLayout layout=new GridLayout(50,1);
        instructArea.setLayout(layout);
    }
    public void add(Task task)
    {
        instructQue.add(task);
        instructArea.add(task.taskLabel,0);
    }
    public void remove(Task task)
    {
        instructQue.remove(task);
        task.taskLabel.setBackground(Color.MAGENTA);
    }
    public void executeInstruct()
    {
        if(this.instructQue.size()!=0&&this.instructQue.getFirst().operation.equals("add")) {
            boolean aTOF = Initializer.memory.requestMemory(this.instructQue.getFirst(), Initializer.firstFit.isSelected());
            //执行成功，移除指令
            if (aTOF)
                this.remove(this.instructQue.getFirst());
            else
                JOptionPane.showMessageDialog(Initializer.mainScreen, "作业" +
                                this.instructQue.getFirst().serialNumber + "申请内存失败",
                        "执行错误", JOptionPane.WARNING_MESSAGE);
        }
        else if(this.instructQue.size()!=0)
        {
            boolean rTOF=Initializer.memory.releaseMemory(this.instructQue.getFirst());
            if(rTOF)
                this.remove(this.instructQue.getFirst());
            else
                JOptionPane.showMessageDialog(Initializer.mainScreen, "作业" +
                         this.instructQue.getFirst().serialNumber + "释放内存失败，不在内存中",
                        "执行错误", JOptionPane.WARNING_MESSAGE);
        }
    }
}
