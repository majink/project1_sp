package org.example;
import java.util.Scanner;

//wordCRUD를 이용해서 전체 관리를 하는 역할을 수행
public class WordManager {
    Scanner sc = new Scanner(System.in);
    WordCRUD wordCRUD;

    WordManager(){
        wordCRUD = new WordCRUD(sc);
    }

    public int selectMenu(){
        System.out.print("*******************\n"
        +                "1. 모든 단어 보기\n"
        +                "2. 수준별 단어 보기\n"
        +                "3. 단어 검색\n"
        +                "4. 단어 추가\n"
        +                "5. 단어 수정\n"
        +                "6. 단어 삭제\n"
        +                "7. 파일 저장\n"
        +                "0. 나가기\n"
        +                "*******************\n"
        +                "=> 원하는 메뉴는? "
        );

        return sc.nextInt();

    }

    public void start() {

        while (true) {
            int menu = selectMenu();
            if(menu == 0 ){
                System.out.println("프로그램 종료! 다음에 만나요 ~");
                break;
            }

            switch (menu) {
                case 1 -> {
                    //모든 단어 보기 함수 호출
                    wordCRUD.listAll();
                }
                case 2 -> {
                    //수분별 단어 보기 함수 호출
                    wordCRUD.levellist();
                }
                case 3 -> {
                }
                //search = 단어 검색 함수 호출
                case 4 ->
                    //add = 단어 추가 함수 호출
                    wordCRUD.addWord();
                case 5 -> {
                    //update = 단어 수정 함수 호출
                    wordCRUD.updateItem();
                }
                case 6 -> {
                    //delete = 단어 삭제 함수 호출
                    wordCRUD.deleteItem();
                }
                case 7 -> {
                    //save = 파일 저장
                }
                default -> {
                    System.out.println("메뉴의 숫자들만 입력해주세요.");
                }
            }
        }

    }

}
