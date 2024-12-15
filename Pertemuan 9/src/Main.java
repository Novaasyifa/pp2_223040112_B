import controller.UserController;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import model.UserMapper;
import view.UserView;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.getSqlSession());

        UserView view = new UserView();
        new UserController(view, mapper);

        view.setVisible(true);
    }
}
