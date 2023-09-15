package org.example;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

//ICRUD를 구현한 것
public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner sc;
    final String fname = "input.txt";

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

    public void addWord(){
        //사용자에게 입력받은 WORD를 LIST에 추가하는 함수
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다 !!! ");
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

    public void listAll(int level){
        if (list.isEmpty()){
            System.out.println("현재 추가된 단어가 없습니다! 단어장에 단어를 추가해주세요.");
        }

        System.out.println("--------------------------------");
        for(int i=0 ; i<list.size() ; i++){
            if(list.get(i).level == level) {
                System.out.print((i + 1) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.println("--------------------------------");
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
        System.out.println("단어가 수정 성공적으로 되었습니다!! ");
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
            list.remove(id-1);
            System.out.println("단어가 삭제되었습니다. ");
        }
        else
            System.out.println("취소되었습니다. ");
    }

    public void levellist() {
        //수준별 단어 보기 함수 출력
        System.out.println("=>단어의 난이도를 숫자로 입력해주세요. 난이도(1, 2, 3) ");
        int level = sc.nextInt();
        this.listAll(level);
    }

    public void search() {
        System.out.print("=> 검색할 단어 입력 : ");
        String sword = sc.next();

        System.out.println("--------------------------------");
        for(int i=0 ; i<list.size() ; i++){
            String word = list.get(i).getWord();
            if(!word.contains(sword)) continue;
            System.out.print((i+1) +" ");
            System.out.println(list.get(i).toString() );
        }
        System.out.println("--------------------------------");
    }

    public void saveFile() throws IOException {
        Writer file = new FileWriter("ouput.txt");

        for(int i =0 ; i<list.size() ; i++ ){
            file.write( list.get(i).toFileString() + '\n' );
        }

        file.close();
        System.out.println("=>파일 저장이 완료 !!!");

    }

    public void loadFile() throws IOException {

        BufferedReader fr = new BufferedReader(new FileReader(fname));
        String line;
        int count = 0;

        while (true){
            line = fr.readLine();
            if(line==null) break;

            String[] data =line.split("\\|");
            int level = Integer.parseInt(data[0]);
            String word = data[1];
            String meaning = data[2];
            list.add(new Word(count,level,word,meaning));
        }

        fr.close();
        System.out.println("==>"+ count + "개 로딩 완료!");
    }
}



