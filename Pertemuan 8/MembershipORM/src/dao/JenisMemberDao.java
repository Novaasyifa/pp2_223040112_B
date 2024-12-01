package dao;

import java.util.List;
import model.JenisMember;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mapper.MemberMapper;

public class JenisMemberDao {

    private final SqlSessionFactory sqlSessionFactory;

    public JenisMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insert(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.insert("mapper.JenisMemberMapper.insert", jenisMember);
        }
        return result;
    }

    public int update(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.update("mapper.JenisMemberMapper.update", jenisMember);
        }
        return result;
    }

    public int delete(JenisMember jenisMember) {
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("mapper.JenisMemberMapper.delete", jenisMember);
        }
        return result;
    }

    public List<JenisMember> findAll() {
        List<JenisMember> result;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.selectList("mapper.JenisMemberMapper.findAll");
        }
        return result;
    }

    public boolean update(Member member) {
        try (SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            mapper.updateMember(member);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int memberId) {
        try (SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            mapper.deleteMember(memberId);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}