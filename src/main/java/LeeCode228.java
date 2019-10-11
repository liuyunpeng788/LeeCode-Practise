import java.util.ArrayList;
import java.util.List;

/**
 *  题目描述
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 *
 * 示例 1:
 *
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 * 示例 2:
 *
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author liumch
 * create :  2019/10/11 15:37
 **/

public class LeeCode228 {
    public static List<String> summaryRanges(int[] nums) {
        //起始位置
        int start = 0;
        List<String> range = new ArrayList<>(nums.length);
        if(nums.length > 0 && nums.length == 1){
            range.add(nums[0] + "");
            return range;
        }else{
            for(int i = 1;i<nums.length;i++){
                if(nums[i] != nums[i-1] + 1 ){
                    if(start == i-1 ||start == nums.length -1){
                        range.add(nums[start] + "");
                    }else{
                        range.add(nums[start] + "->" + nums[i-1]);
                    }
                    start = i;
                }
            }
            //到达最后一个元素
            if(start == nums.length -1){
                range.add(nums[nums.length -1] + "");
            }else{
                range.add(nums[start] + "->" + nums[nums.length-1]);
            }
        }

        return range;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,3,4,6,8,9};
		summaryRanges(nums).forEach(System.out::println);
    }

}
