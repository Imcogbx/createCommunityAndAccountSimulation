import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class mainClass {
    public static Scanner scanner = new Scanner(System.in);
    public static Map<String, String> account = new HashMap<>();
    public static Map<String, String> login = new HashMap<>();
    public static Map<String, String> communityPost = new HashMap<>();
    public static List<String> community = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("post 라는 패키지를 만드셨나요?\n그렇지 않다면 만드신 후 다시 실행해주세요!");
        while (true) {
            System.out.println("원하시는 작업 번호를 적어주세요");
            System.out.println("1. 설명");
            System.out.println("2. 계정 생성");
            System.out.println("3. 계정 로그인");
            System.out.println("4. 계정 로그아웃");
            System.out.println("5. 로그인 계정 확인");
            System.out.println("6. 게시물 추가");
            System.out.println("7. 커뮤니티 생성");
            System.out.println("8. 커뮤니티 확인");
            System.out.println("9. 커뮤니티 게시글 등록");
            System.out.println("10. 커뮤니티 게시글 등록해제");
            System.out.println("11. 종료");
            String development = scanner.nextLine();
            int developmentInt = pauseInt(development);
            if (developmentInt == 1) {
                readInformation();
            } else if (developmentInt == 2) {
                createAccount();
            } else if (developmentInt == 3) {
                loginAccount();
            } else if (developmentInt == 4) {
                logoutAccount();
            } else if (developmentInt == 5) {
                getLoginAccount();
            } else if (developmentInt == 6) {
                createPost();
            } else if (developmentInt == 7) {
                createCommunity();
            } else if (developmentInt == 8) {
                getCommunity();
            } else if (developmentInt == 9) {
                communityPostJoin();
            } else if (developmentInt == 10) {
                communityPostJoinOut();
            } else if (developmentInt == 11) {
                System.out.println("프로그램을 3초후에 종료합니다.");
                delay(3);
                break;
            }
        }
    }

    private static void readInformation() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/Information.txt");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾지 못했습니다.");
        }
        Scanner information = new Scanner(inputStream);
        while (information.hasNextLine()) {
            System.out.println(information.nextLine());
            delay(2);
        }
    }

    private static void createAccount() {
        System.out.println("아이디와 비밀번호를 만들어주세요");
        String id = scanner.nextLine();
        String password = scanner.nextLine();
        System.out.println("계정 생성에 성공하셨습니다!");
        System.out.println("아이디: " + id + "\n" + "비밀번호: " + password);
        account.put(id, password);
    }

    private static void loginAccount() {
        while (true) {
            System.out.println("아이디와 비밀번호를 입력해주세요");
            String id = scanner.nextLine();
            if (id.equals("종료")) {
                System.out.println("로그인을 종료합니다.");
                delay(3);
                break;
            } else {
                String password = scanner.nextLine();
                String get = account.get(id);
                try {
                    if (get.equals(password)) {
                        System.out.println("계정 로그인에 성공하셨습니다!");
                        login.put(id, password);
                        break;
                    } else {
                        System.out.println("비밀번호가 틀립니다.");
                    }
                } catch (NullPointerException e) {
                    System.out.println("아이디가 올바르지 않습니다...");
                }
            }
        }
    }

    private static void logoutAccount() {
        while (true) {
            System.out.println("아이디와 비밀번호를 입력해주세요");
            String id = scanner.nextLine();
            if (id.equals("종료")) {
                System.out.println("로그아웃을 종료합니다.");
                delay(3);
                break;
            } else {
                String password = scanner.nextLine();
                String get = account.get(id);
                try {
                    if (get.equals(password)) {
                        System.out.println("계정 로그아웃에 성공하셨습니다!");
                        login.remove(id, password);
                        break;
                    } else {
                        System.out.println("비밀번호가 틀립니다.");
                    }
                } catch (NullPointerException e) {
                    System.out.println("아이디가 올바르지 않습니다...");
                }
            }
        }
    }

    private static void getLoginAccount() {
        if (login.isEmpty()) {
            System.out.println("로그인한 계정이 없습니다.");
        } else {
            int size = login.size();
            System.out.println("로그인한 계정의 갯수는 " + size + "개 입니다.");
            System.out.println(login);
        }
    }

    private static void createPost() {
        while (true) {
            FileWriter writer = null;
            System.out.println("게시글의 제목을 적어주세요");
            String fileName = scanner.nextLine();
            if (fileName.equals("종료")) {
                System.out.println("게시글 생성을 3초후에 종료합니다.");
                delay(3);
                break;
            } else {
                try {
                    writer = new FileWriter("src/post/" + fileName + ".txt");
                } catch (IOException e) {
                    System.out.println("게시글의 제목을 만드는데 실패하였습니다.");
                    return;
                }
                System.out.println("게시글의 내용을 적어주세요");
                System.out.println("종료: 빈 줄에서 엔터");
                while (true) {
                    String input = scanner.nextLine();
                    if (input.equals("")) {
                        break;
                    }
                    try {
                        writer.write(input);
                        writer.write("\n");
                    } catch (IOException e) {
                        System.out.println("게시글 내용 쓰기에 실패하였습니다.");
                    }
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("게시글 생성을 종료하는데 실패하였습니다.");
                }
            }
        }
    }

    private static void createCommunity() {
        while (true) {
            System.out.println("커뮤니티의 이름을 정해주세요");
            String name = scanner.nextLine();
            if (name.equals("종료")) {
                System.out.println("커뮤니티 생성을 3초후에 종료합니다.");
                delay(3);
                break;
            } else {
                community.add(name);
                System.out.println("커뮤니티 생성에 성공하였습니다.");
            }
        }
    }

    private static void getCommunity() {
        if (community.isEmpty()) {
            System.out.println("커뮤니티가 없습니다.");
        } else {
            int size = community.size();
            System.out.println("커뮤니티의 갯수는 " + size + "개 입니다.");
            System.out.println(community);
        }
    }

    private static void communityPostJoin() {
        while (true) {
            System.out.println("등록할 커뮤니티와 게시글의 이름을 적어주세요.");
            String community1 = scanner.nextLine();
            if (community1.equals("종료")) {
                System.out.println("등록을 3초후에 종료합니다.");
                delay(3);
                break;
            } else {
                if (community.contains(community1)) {
                    String post = scanner.nextLine();
                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream("src/post/" + post + ".txt");
                        communityPost.put(community1, post);
                        System.out.println("등록에 성공하셨습니다!");
                    } catch (FileNotFoundException e) {
                        System.out.println("파일 찾기에 실패하였습니다.");
                    }
                } else {
                    System.out.println("커뮤니티가 없습니다.");
                }
            }
        }
    }

    private static void communityPostJoinOut() {
        while (true) {
            System.out.println("등록해제할 커뮤니티와 게시글의 이름을 적어주세요.");
            String community1 = scanner.nextLine();
            if (community1.equals("종료")) {
                System.out.println("등록을 3초후에 종료합니다.");
                delay(3);
                break;
            } else {
                if (community.contains(community1)) {
                    String post = scanner.nextLine();
                    FileInputStream inputStream = null;
                    try {
                        inputStream = new FileInputStream("src/post/" + post + ".txt");
                        communityPost.remove(community1, post);
                        System.out.println("등록에 성공하셨습니다!");
                    } catch (FileNotFoundException e) {
                        System.out.println("파일 찾기에 실패하였습니다.");
                    }
                } else {
                    System.out.println("커뮤니티가 없습니다.");
                }
            }
        }
    }

    public static void delay(long a) {
        try {
            Thread.sleep(a*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int pauseInt(String Int) {
        try {
            return Integer.parseInt(Int);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
