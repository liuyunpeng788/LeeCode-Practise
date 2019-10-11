import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 注意：本题暂时还没有通过测试、需要进一步修改
 *
 * 输入一个按升序排序的整数数组（可能包含重复数字），你需要将它们分割成几个子序列，其中每个子序列至少包含三个连续整数。返回你是否能做出这样的分割？
 *
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *  
 *
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *  
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *  
 *
 * 提示：
 *
 * 输入的数组长度范围为 [1, 10000]
 *
 * @author liumch
 * create :  2019/10/11 15:47
 **/

public class LeeCode659 {
    public static boolean generalSerialSubArray(int[] nums){
        if(nums.length == 0){
            return false;
        }
        List<List<Integer>> list = new ArrayList<>(50);
        //原始的栈
        Stack<Integer> srcStack = new Stack<>();
        //临时栈
        Stack<Integer> tmpStack = new Stack<>();
        for (int i = nums.length -1;i>=0;i-- ) {
            srcStack.push(nums[i]);
        }
        int prepElm = 0 ;
        List<Integer> elements = new ArrayList<>(10);
        while(!srcStack.isEmpty()){
            int elm = srcStack.pop();
            if(prepElm == 0){
                prepElm = elm;
                elements.add(elm);
            }else if(prepElm == elm){
                //判断elements元素数量是否已经够3个，
                // 如果够，则将其添加到列表中，否则，将当前元素添加到临时栈
                //不连续，需要判断list中是否已经满足大小为3的要求。否则，清空
                if(elements.size() >= 3){
                    list.add(elements);
                    elements = new ArrayList<>(10);
                    if(tmpStack.isEmpty()){
                        elements.add(elm);
                        prepElm = elm;
                    }else{
                        while(!tmpStack.isEmpty()){
                            srcStack.push(tmpStack.pop());
                        }
                    }
                }else{
                    tmpStack.push(elm);
                }

            }else if(prepElm + 1 != elm){
                //不连续，需要判断list中是否已经满足大小为3的要求。否则，清空
                if(elements.size() >= 3){
                    list.add(elements);
                    elements = new ArrayList<>(10);
                }
                if(tmpStack.isEmpty()){
                    elements.add(elm);
                    prepElm = elm;
                }else{
                    while(!tmpStack.isEmpty()){
                        srcStack.push(tmpStack.pop());
                    }
                }

            }else{
                //连续
                if(elements.size() >= 3){
                    list.add(elements);
                    elements = new ArrayList<>(10);
                    if(tmpStack.isEmpty()){
                        elements.add(elm);
                        prepElm = elm;
                    }else{
                        while(!tmpStack.isEmpty()){
                            srcStack.push(tmpStack.pop());
                        }
                    }
                }else{
                    elements.add(elm);
                    prepElm = elm;
                }


            }

        }
        if(!elements.isEmpty() && elements.size()>=3){
            list.add(elements);
        }
        if(list.size()>=2){
//			Collections.reverse(list);
            list.forEach(x->{x.forEach(System.out::print);
                System.out.println();});
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6};
        boolean res = generalSerialSubArray(nums);
        System.out.println(res);
    }
}
