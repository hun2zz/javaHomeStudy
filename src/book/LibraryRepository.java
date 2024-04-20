package book;

public class LibraryRepository {
    private static BookUser user;
    private static BookList bookList;

    static  {
        bookList = new BookList();

        bookList.push(new CookBook("기적의 집밥책", "김해진", "청림라이프", true));
        bookList.push(new CartoonBook("떨어지면 끝장맨", "스에노부 케이코", "대원씨아이", 18));
        bookList.push(new CartoonBook("원펀맨", "One", "대원씨아이", 15));
        bookList.push(new CookBook("삐뽀삐뽀 119 이유식", "하정훈", "유니책방", false));
        bookList.push(new CookBook("오늘은 아무래도 덮밥", "이마이 료", "참돌", true));
        bookList.push(new CartoonBook("이세계로 전이했으니 치트를 살려 마법검사가 되기로 했다", "Shinkoshoto", "대원씨아이", 12));
    }

    public void saveBookUser(BookUser bookUser) {
        user = bookUser;
    }

    public BookUser getCurrentUser() {
        return user;
    }

//    public void showInfo () {
//        System.out.println("******* 회원님 정보 *******");
//        System.out.println("# 회원명 : "+ user.getName());
//        System.out.println("# 나이 : "+ user.getAge());
//        System.out.println("# 남성 : "+ user.getGenderToString());
//        System.out.println("# 쿠폰개수 : "+ user.getCouponCount());
//    }
}
