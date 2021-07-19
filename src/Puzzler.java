
package demo;
import static java.util.Arrays.*;


public class Puzzler{
  
  public static final int SIZE =9;
  private boolean[][] fixed =new boolean[SIZE][SIZE];
  private int[][] number =new int[SIZE][SIZE];
 
  public Puzzler(int[][] p){
    setPuzzler(p);
  }
  /**
  * 用一个二维数组去设置该数独
  * 注意:这个二维数组应该只包含0~9中的数字,为0时表示该处留空
  * 该方法假设p的数据是合法的,不对其进行任何检查
  */
  public void setPuzzler(int[][] p){
    for(int i=0;i<SIZE;i++)
      for(int j=0;j<SIZE;j++){
        if(p[i][j] ==0){
          fixed[i][j] =false;
          number[i][j] =0;
        } else{
          number[i][j] =p[i][j];
          fixed[i][j] =true;
        }
     }
  }
  /**
  * 清除
  */
  public void clear(){
    for(int n=0;n<SIZE;n++){
      fill(fixed[n],false);
      fill(number[n],0);
    }
    return;
  }
  /**
  * 位置i,j是否固定,如果固定表示该处的数字不能更改
  */
  public boolean isFixed(int i,int j){
    return fixed[i][j];
  }
  /**
  * 得到位置i,j处的数字
  */
  public int getNumber(int i,int j){
    return number[i][j];
  }
  /**
  * 设置i,j处的数字.如果该处数字是固定的,将抛出异常
  */
  public void setNumber(int i,int j,int num){
    number[i][j] =num;
  }
}
