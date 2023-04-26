//Bruteforce recursion   time complexity : logarithmic time  space complexity : O(n)  stack space
class Solution {
    int mod=1000000007;
    public int numberOfArrays(String s, int k) {
        int n=s.length();

        return solve(0,s,k,n);
    }
    int solve(int ind,String  s,int k,int n){
        if(ind>=n)
        return 1;

        if(s.charAt(ind)=='0')return 0;
        
        long poss_way=0;
        long cur_num=0;
        for(int i=ind;i<n;i++){
            cur_num=cur_num*10+(s.charAt(i)-'0');
            if(cur_num>k){
                break;
            }else
            poss_way=(poss_way+solve(i+1,s,k,n))%mod;
        }
        return (int)(poss_way%mod);
    }
}

//memoization ( dp )  time complexity : O(n)  space complexity : O(n)+O(n)  stack space
class Solution {
    int mod=1000000007;
    public int numberOfArrays(String s, int k) {
        int n=s.length();
        int dp[]=new int[n];
        Arrays.fill(dp,-1);

        return solve(0,s,k,n,dp);
    }
    int solve(int ind,String  s,int k,int n,int dp[]){
        if(ind>=n)
        return 1;

        if(s.charAt(ind)=='0')return 0;

        if(dp[ind]!=-1)
        return dp[ind];

        long poss_way=0;
        long cur_num=0;
        for(int i=ind;i<n;i++){
            cur_num=cur_num*10+(s.charAt(i)-'0');
            if(cur_num>k){
                break;
            }else
            poss_way=(poss_way+solve(i+1,s,k,n,dp))%mod;
        }
        return dp[ind]=(int)(poss_way%mod);
    }
}

// Tabulation    time complexity : O(n)  space complexity : O(n) remove extra stack space used in memoization 
class Solution {
    int mod=1000000007;
    public int numberOfArrays(String s, int k) {
        int n=s.length();
        int dp[]=new int[n+1];
        dp[n]=1;
        for(int ind=n-1;ind>=0;ind--){
            if(s.charAt(ind)=='0'){
                dp[ind]=0;
                continue;
            }
            long poss_way=0;
            long cur_num=0;
            for(int i=ind;i<n;i++){
                cur_num=cur_num*10+(s.charAt(i)-'0');
                if(cur_num>k){
                   break;
                }else
                poss_way=(poss_way+dp[i+1])%mod;
            }
            dp[ind]=(int)(poss_way%mod);
        }

        return dp[0]%mod;
    }
}
