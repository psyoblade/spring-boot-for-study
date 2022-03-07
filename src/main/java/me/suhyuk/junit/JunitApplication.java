package me.suhyuk.junit;

import me.suhyuk.junit.domain.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

/**
 * JPA Buddy 활용한 Entity 생성
 *
 * 1. 반드시 JPA Buddy 통해서만 Entity 생성을 합니다
 * 2.
 */
@SpringBootApplication
public class JunitApplication {

	public static void main(String[] args) {
		// SpringApplication.run(JunitApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Foo foo = new Foo();
			foo.setName("bar");
			em.persist(foo);

			// TODO: 1. Connector { id, name, createdBy, createdTime }

			// TODO: 2. HDFSConnector { path } 상속 "단일테이블전략", @SuperBuilder 통한 @Builder 상속

			// TODO: 3. BaseConnector, HiveConnector @MappedSuperClass 사용한 "조인전략" 수행합니다

			// TODO: 4. KafkaConnector 콜렉션 필드는 @ElementCollection 활용합니다

			// TODO: 5. 1:1 관계 : User -> UserDetail - 이용자 상세정보

			// TODO: 6. 1:N 단방향 관계 : User -> KafkaConnector - 이용자가 커넥터를 추가한다

			// TODO: 7. 1:N 양방향 관계 : Team -> User - 팀(1)에서 사람(N)을 채용하고, 사람의 팀을 확인한다

			// TODO: 8. N:1 관계 : User -> Team - 여러 이용자(N)가 팀(1)에 합류하다

			// TODO: 9. N:M 관계 : Team -> TeamTag <- Tag - N:M 대신 1:N + N:1 방식으로 대체
			//                    하나의 팀은 여러 역할을 가질 수 있고, 하나의 역할에 여러 팀이 할당될 수 있다

			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			emf.close();
		}

	}

}
