package book;

import util.SimpleInput;

public class LibraryView {
    SimpleInput si;
    LibraryRepository repository;
    BookUser bu;


    LibraryView() {
        this.si = new SimpleInput();
        this.repository = new LibraryRepository();
        this.bu = new BookUser();

    }

    void showInput() {
        System.out.println("# 회원 정보를 입력해주세요.");
        String name = SimpleInput.input("# 회원 이름 : ");
        int age = Integer.parseInt(SimpleInput.input("# 회원 나이 : "));
        Gender gender = inputGender();

        repository.saveBookUser(new BookUser(name, age, gender, 0));
    }

    private Gender inputGender() {
        while (true) {
            String gender = SimpleInput.input("# 성별(M/F) : ").toUpperCase();
            if (gender.startsWith("M")) return Gender.MALE;
            if (gender.startsWith("F")) return Gender.FEMALE;
            System.out.println("성별을 잘못 입력했습니다.");
        }
    }

    void showMenu() {
        System.out.println("============도서메뉴============");
        System.out.println("# 1. 마이페이지");
        System.out.println("# 2. 도서 전체 조회");
        System.out.println("# 3. 도서 제목으로 검색");
        System.out.println("# 4. 도서 대여하기");
        System.out.println("# 5. 도서 저자이름으로 검색");
        System.out.println("# 9. 프로그램 종료하기");
    }

    void showPlayerMenu(){
        BookUser currentUser = repository.getCurrentUser();
        System.out.println("******* 회원님 정보 *******");
        System.out.println("# 회원명 : " + currentUser.getName());
        System.out.println("# 나이 : " + currentUser.getAge());
        System.out.println("# 성별 : " + currentUser.getGenderToString());
        System.out.println("# 쿠폰개수 : " + currentUser.getCouponCount());
    }

    void showBookList(){
        Book[] booklist = repository.getBookList();
        for (int i = 0; i < booklist.length; i++) {
            Book book = booklist[i];
            System.out.printf("%d. %s , 저자 : %s\n",i+1, book.info(), book.getAuthor());

        }
    }


    void containBookList(){
        String bookName = SimpleInput.input("# 찾으려는 책의 제목 : ");
        Book[] booklist = repository.getBookList();
        for (Book book : booklist) {
            if(book.getTitle().contains(bookName)) {
                System.out.println(book.info());
            }
        }
    }


    void rentBook() {
        showBookList();
        int rentBookInt = Integer.parseInt((SimpleInput.input("# 대여할 책의 번호 : ")));
        RentStatus rent = repository.rentBook(rentBookInt);
        if (rent == RentStatus.RENT_FAIL) {
            System.out.println("# 대여에 실패하였습니다.");
        } else if(rent == RentStatus.RENT_SUCCESS) {
            System.out.println("# 대여에 성공하였습니다.");
        } else if(rent == RentStatus.RENT_SUCCESS_WITH_COUPON) {
            System.out.println("# 대여에 성공 후 쿠폰 발급이 완료 됐습니다.");
        }

    }

    void authorSearch() {
        String bookName = SimpleInput.input("# 찾으려는 책의 저자 : ");
        boolean seach = false;
        Book[] booklist = repository.getBookList();
        for (Book book : booklist) {
            if(book.getAuthor().equals(bookName)) {
                System.out.println(book.info());
                seach = true;
            }
        }
        if (!seach){
            System.out.println("그런 저자는 없습니다.");
        }
    }
    public void start() {
        showInput();
        while (true) {
            showMenu();
            String menuNum = SimpleInput.input(" - 메뉴번호 : ");
            switch (menuNum) {
                case "1":
                    showPlayerMenu();
                    break;
                case "2":
                    showBookList();
                    break;
                case "3":
                    containBookList();
                    break;
                case "4":
                    rentBook();
                    break;
                case "5":
                    authorSearch();
                    break;
                case "9":
                    System.out.println("# 시스템을 종료합니다");
                    return;
                default:
                    System.out.println("# 올바른 메뉴 번호를 입력해주세요.");
            }
        }
    }

}
