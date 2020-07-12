package com.gs.leaf.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringUtil {


    @Test
    public void test() {
        String str = "123";
        String str1 = "12";

        System.out.println(str.indexOf(str1));


    }

    public boolean isPalindrome(int x) {
        if (x<0) return false;
        if (x >= Integer.MAX_VALUE) return false;
        int temp=0,n =x;

        while (n !=0){
            int num = n%10;
            n/=10;
            temp = temp*10+num;
        }

        return  temp ==x;
    }

    @Test
    public void testIsValid() {
        System.out.println(isValid("[]"));

    }

    public boolean isValid(String s) {
        if (s == null || s.length() ==0) return false;
        int length = s.length();
        if (length%2 != 0) return false;
        HashMap<Character,Character> map = new HashMap<Character,Character>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length ; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)){
                stack.push(c);
            }else {
                if (!stack.isEmpty() && map.get(c) != stack.pop()){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    @Test
    public void testMergeTwoLists() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        l4.next = l5;
        l5.next = l6;
        ListNode listNode = mergeTwoLists(l1, l4);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;

        ListNode res = new ListNode(0);
        ListNode res1 = res;

        while (p != null || q != null){

            if (p == null){
                res.next = q;
                break;
            }

            if (q == null){
                res.next = p;
                break;
            }

            if (p.val < q.val){
                res.next = p;
                res=res.next;
                p = p.next;
            }else{
                res.next = q;
                res=res.next;
                q = q.next;
            }
        }

        return res1.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
