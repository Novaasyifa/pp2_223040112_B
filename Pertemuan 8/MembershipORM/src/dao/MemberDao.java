package dao;

import java.util.List;
import mapper.MemberMapper;
import model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    // Konstruktor
    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Menambahkan data Member baru.
     */
    public int insert(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            int result = mapper.insert(member);
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Memperbarui data Member berdasarkan ID.
     */
    public int update(Member member) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            int result = mapper.update(member);
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Menghapus data Member berdasarkan ID.
     */
    public int delete(int memberId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            int result = mapper.delete(memberId);
            session.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Mengambil semua data Member.
     */
    public List<Member> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            return mapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
