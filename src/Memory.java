import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * created by kz on
 */
public class Memory extends JPanel {
    JPanel memoryPanel=new JPanel();
    LinkedList<StorageBlock> filledList=new LinkedList<>();
    LinkedList<StorageBlock> emptyList=new LinkedList<>();

    public Memory(){
        memoryPanel.setBounds(10,10,250,640);
        memoryPanel.setBackground(Color.GRAY);
        emptyList.add(new StorageBlock(0,640,640));
        memoryPanel.add(emptyList.getFirst().block);
        paintLocation();
    }

    //释放内存
    public boolean releaseMemory(Task task)
    {
        StorageBlock storageBlock=new StorageBlock(0,0,0);
        storageBlock.serialNumber=task.serialNumber;
        boolean success=false;
        for(StorageBlock node:filledList)
        {
            if(node.serialNumber==storageBlock.serialNumber)
            {
                storageBlock.start=node.start;
                storageBlock.end=node.end;
                storageBlock.size=node.size;
                filledList.remove(node);
                memoryPanel.remove(node.block);
                memoryPanel.updateUI();
                success=true;
                break;
            }
        }
        if(!success)
            return false;
        for(int i=0;i<emptyList.size();i++)
            if(storageBlock.start==emptyList.get(i).end)
            {
                emptyList.get(i).end+=storageBlock.size;
                emptyList.get(i).size+=storageBlock.size;

                //遍历空内存块，这是释放块处于两空闲块之间的情况，合并三块
                if(i+1<emptyList.size()&&emptyList.get(i).end==emptyList.get(i+1).start)
                {
                    emptyList.get(i).end=emptyList.get(i+1).end;
                    emptyList.get(i).size+=emptyList.get(i+1).size;
                    memoryPanel.remove(emptyList.get(i+1).block);
                    emptyList.remove(i+1);
                }
                emptyList.get(i).reNewEmptyBlock();
                paintLocation();
                return true;
            }
            //释放块位于空闲块之前
            else if(storageBlock.end==emptyList.get(i).start)
            {
                emptyList.get(i).start=storageBlock.start;
                emptyList.get(i).size+=storageBlock.size;
                emptyList.get(i).reNewEmptyBlock();
                paintLocation();
                return true;
            }
         //不能连成块，建立新块
        for(int i=0;i<emptyList.size();i++)              //新建块插入时维持空闲块链表有序
            if(emptyList.get(i).start>storageBlock.start)
                if(emptyList.size()!=1)
                {
                    emptyList.add(i,storageBlock);break;
                }
                else
                {
                    emptyList.add(0,storageBlock);break;
                }
        memoryPanel.add(storageBlock.block);
        storageBlock.reNewEmptyBlock();
        memoryPanel.updateUI();
        storageBlock.block.setLocation(0,storageBlock.start);
        paintLocation();
        return true;
    }

    //申请内存
    public boolean requestMemory(Task task,boolean algorithm)
    {
        int min=-1;
        int minSize=1000;
        if(algorithm)
            for(int i=0;i<emptyList.size();i++)
            {
                if(emptyList.get(i).size>=task.taskSize)
                {
                    filledList.add(new StorageBlock(task));
                    memoryPanel.add(filledList.getLast().block);
                    filledList.getLast().block.setLocation(0,emptyList.get(i).start);
                    filledList.getLast().start=emptyList.get(i).start;
                    filledList.getLast().end=emptyList.get(i).start+task.taskSize;
                    memoryPanel.updateUI();

                    //修改空闲分区表
                    emptyList.get(i).size-=task.taskSize;
                    emptyList.get(i).start+=task.taskSize;
                    emptyList.get(i).reNewEmptyBlock();
                    paintLocation();
                    if(emptyList.get(i).size==0)
                        emptyList.remove(i);
                    return true;
                }
            }

        //最佳适配算法
        else {
            for(int i=0;i<emptyList.size();i++)
                if(emptyList.get(i).size>=task.taskSize)
                {
                    if(emptyList.get(i).size<minSize)
                    {
                        min=i;
                        minSize = emptyList.get(i).size;
                    }
                }
            if(min!=-1)
            {
                filledList.add(new StorageBlock(task));
                memoryPanel.add(filledList.getLast().block);
                filledList.getLast().block.setLocation(0,emptyList.get(min).start);
                filledList.getLast().start=emptyList.get(min).start;
                filledList.getLast().end=emptyList.get(min).start+task.taskSize;
                memoryPanel.updateUI();
                //修改空闲分区表
                emptyList.get(min).size-=task.taskSize;
                emptyList.get(min).start+=task.taskSize;
                emptyList.get(min).reNewEmptyBlock();
                paintLocation();
                if(emptyList.get(min).size==0)
                    emptyList.remove(min);
                return true;
            }
        }
        //分配失败
        return false;
    }
    //还原内存界面
    public void clear()
    {
        emptyList.clear();
        emptyList.add(new StorageBlock(0,640,640));
        filledList.clear();
        memoryPanel.removeAll();
        memoryPanel.add(emptyList.getFirst().block);
        paintLocation();
        memoryPanel.updateUI();
    }

    public void paintLocation()
    {
        for(StorageBlock s:emptyList)
            s.paintStartEnd();
        for (StorageBlock b:filledList)
            b.paintStartEnd();
    }

}