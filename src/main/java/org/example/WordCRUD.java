package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

//ICRUD를 구현한 것
public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner sc;

    WordCRUD(Scanner sc){
        list = new ArrayList<>();
        this.sc = sc;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1, 2, 3) & 새 단어 입력 : ");
        int level = sc.nextInt();
        String word = sc.nextLine();
        //엔터를 실수로 입력했을 상황과 영어가 아닐 시에 예외 처리 하기
        if(word == ""){
            System.out.println("단어를 다시 입력해주세요.");
            word = sc.nextLine();
        }

        System.out.print("뜻 입력 : ");
        String meaning = sc.nextLine();

        return new Word(0, level , word, meaning);
    }

    public void addWord(){
        //사용자에게 입력받은 WORD를 LIST에 추가하는 함수
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다 !!! ");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll(){
        if (list.isEmpty()){
            System.out.println("현재 추가된 단어가 없습니다! 단어장에 단어를 추가해주세요.");
        }

        System.out.println("--------------------------------");
        for(int i=0 ; i<list.size() ; i++){
            System.out.print((i+1) +" ");
            System.out.println(list.get(i).toString() );
        }
        System.out.println("--------------------------------");
    }

    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0 ;

        if (list.isEmpty()){
            System.out.println("현재 추가된 단어가 없습니다! 단어장에 단어를 추가해주세요.");
        }

        System.out.println("--------------------------------");
        for(int i=0 ; i<list.size() ; i++){
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) +" ");
            System.out.println(list.get(i).toString() );
            idlist.add(i);
            j++;
        }
        System.out.println("--------------------------------");

        return idlist;
    }

    public void updateItem() {
        System.out.println("=> 수정할 단어 검색 : ");
        String keyword = sc.next();
        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 수정할 번호 선택 : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("=> 뜻 입력 : ");
        String meaning = sc.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }

    public void deleteItem() {
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = sc.next();
        ArrayList<Integer> idlist = this.listAll(keyword);

        System.out.print("=> 삭제할 번호 선택 : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans = sc.next();
        if(ans.equalsIgnoreCase("y")){
            list.remove(idlist.get(id-1));
            System.out.println("단어가 삭제되었습니다. ");
        }
        else
            System.out.println("취소되었습니다. ");
    }
}
