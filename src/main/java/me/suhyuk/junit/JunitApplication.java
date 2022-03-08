package me.suhyuk.junit;

import me.suhyuk.junit.domain.Foo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * TODO:  JPA Buddy 활용한 Entity 생성
 *  1. 반드시 JPA Buddy 통해서만 Entity 생성을 합니다
 *  2. 모든 연관 관계는 1:1, 1:N 관계만 유지합니다
 *  3. 연관 테이블의 트랜잭션이 동일한 경우는 MappedSuperClass 혹은 ElementCollection 방식을 지향합니다
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

			// TODO: 1. BasePath { path }
			//  EXPECTED : PATH 테이블에 레코드 생성

			// TODO: 2. HDFSPath { hdfsPath } extends BasePath 상속 "단일테이블전략"
			//          LocalPath { localPath } extends BasePath
			//          @SuperBuilder 통한 @Builder 상속
			//  EXPECTED : 부모 테이블에 모든 스키마가 추가되어 2개의 레코드가 생성됨

			// TODO: 3. HiveConnector { serverURL } extends Connector { name, createdTime }
			//          ClickHouseConnector { jdbcConnString } extends Connector
			//          @MappedSuperClass 사용한 "조인전략" - 자식클래스에게 매핑정보만 제공
			//  EXPECTED : 별도의 자식 테이블들이 생성되며, 부모 테이블은 생성되지 않음

			// TODO: 4. KafkaConnector { List<String> brokers } extends Connector
			//          @ElementCollection @CollectionTable 통한 "콜렉션필드전략"
			//  EXPECTED : 부모 자식 테이블 모두 생성 됩니다

			// TODO: 5. 1:1 단방향 관계 : Member { name } -> MemberDetail { nickName } - 이용자 상세정보 (User is reserved keyword)
			//  EXPECTED : 두 테이블 모두 생성됩니다

			// TODO: 6. 1:1 양방향 관계 : Member -> MemberLocker - 이용자 락커
			//  EXPECTED : 두 테이블 모두 생성됩니다

			// TODO: 7. 1:N 단방향 관계 : Team <- Member - 팀이 팀원이 채용한다 (1이 연관관계의 주인, FK를 가지는 테이블)
			//  EXPECTED : 팀은 독립적인 테이블이고, 멤버에만 팀의 외래키를 가진다

			// TODO: 7. 1:N 양방향 관계 : Member <- KafkaConnector - 이용자가 커넥터를 생성한다
			//  EXPECTED : 카프카 커넥터 테이블에 멤버 외부키가 존재합니다

			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			emf.close();
		}

	}

}
