import java.util.*;

public class Frogjmp {

//-----------------------RECURSION-------------------------------------------------    
    public static int frogjmp(int dp[],int i){
        int ans = 0;

        if(i == 0){
            return 0;
        }
        int left = frogjmp(dp, i-1) + Math.abs(dp[i] - dp[i-1]);
        int right = Integer.MAX_VALUE;

        if(i >1){
            right = frogjmp(dp, i-2) + Math.abs(dp[i] - dp[i-2]);
        }
        ans = Math.min(left,right);
        return ans;
    }

//----------------------memoization-------------------------------------------------
public static int frogJmpmemo(int i ,int dp[],int arr[])    {
    if(i == 0){
        return 0;
    }
    if(dp[i] != -1){
        return dp[i];
    }
    int left  = frogJmpmemo(i-1,dp,arr) + Math.abs(arr[i] - arr[i-1]);
    int right = Integer.MAX_VALUE;

    if(i >1){
        right = frogJmpmemo( i-2,dp,arr) + Math.abs(arr[i] - arr[i-2]);
    }
    dp[i] = Math.min(left,right);
    return dp[i];  
}

//------------------------------------------tabulation---------------------------------        
public static int frogJumpTabu(int n,int dp[],int arr[]){
    dp[0] =0;
    for(int i=1;i<n;i++){
        int fs = dp[i-1] + Math.abs(arr[i] - arr[i-1]);
        int ss = Integer.MAX_VALUE;

        if(i >1){
            ss =  dp[i-2] + Math.abs(arr[i] - arr[i-2]);
        }
        dp[i] = Math.min(fs,ss);
    }
    return dp[n-1];

}
//-------------------------space optimization---------------------------------------------
    public static int frogJumpSo(int n , int arr[]){
        int prev1=0;
        int prev2=0;

        for(int i=1;i<=n;i++){
            int fs = prev1 + Math.abs(arr[i] - arr[i-1]);
            int ss = Integer.MAX_VALUE;

            if(i >1){
                ss = prev2 + Math.abs(arr[i] - arr[i-2]);
            }
            int curr = Math.min(fs,ss);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    public static void main(String args[]){

        int arr[] = {10, 30, 40, 20};
        int n = arr.length;
//------------------------------------------recusion---------------------------------        
        //System.out.println(frogjmp(arr, n-1));
//------------------------------------------memoization---------------------------------        

        int dp[] = new int[n+1];
        Arrays.fill(dp,-1);
        //System.out.println(frogJmpmemo(n-1, dp, arr));
        //System.out.println(frogJumpTabu(n-1, dp, arr));
        System.out.println(frogJumpSo(n-1,arr));

//------------------------------------------tabulation---------------------------------        
        
    }
}
