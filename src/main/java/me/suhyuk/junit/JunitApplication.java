package me.suhyuk.junit;

import me.suhyuk.junit.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

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
			/**
			Foo foo = new Foo();
			foo.setName("bar");
			em.persist(foo);

			// TODO: 1. BasePath { path }
			//  EXPECTED : 정상적으로 레코드 생성
			Path path = Path.builder().path("/source/path").build();
			em.persist(path);

			// TODO: 2. HDFSPath { scheme } extends BasePath 상속 "단일테이블전략"
			//          @SuperBuilder 통한 @Builder 상속
			//  EXPECTED : 부모 테이블에 모든 스키마가 추가되어 2개의 레코드가 생성됨
			HDFSPath hdfsPath = HDFSPath.builder().basePath("/base/path").scheme("hdfs://").build();
			em.persist(hdfsPath);

			LocalPath localPath = LocalPath.builder().basePath("/base/path").localPath("file://").build();
			em.persist(localPath);

			// TODO: 3. Connector { name, createdBy, createdTime } HiveConnector extends Connector
			//          @MappedSuperClass 사용한 "조인전략" - 자식클래스에게 매핑정보만 제공
			//  EXPECTED : 별도의 자식 테이블들이 생성되며, 부모 테이블은 생성되지 않음
			HiveConnector hiveConnector = HiveConnector.builder()
					.name("hive-connector")
					.createdBy("psyoblade")
					.createdTime(LocalDateTime.now())
					.serverURI("localhost:10000")
					.build();
			em.persist(hiveConnector);

			ClickhouseConnector clickhouseConnector = ClickhouseConnector.builder()
					.name("clickhouse-connector")
					.createdBy("psyoblade")
					.createdTime(LocalDateTime.now())
					.jdbcConnectionString("jdbc://localhost:9000")
					.build();
			em.persist(clickhouseConnector);

			// TODO: 4. KafkaConnector { List<String> brokers } extends Connector
			//          @ElementCollection @CollectionTable 통한 "콜렉션필드전략"
			KafkaConnector kafkaConnector = KafkaConnector.builder()
					.name("kafka-connector")
					.createdBy("psyoblade")
					.createdTime(LocalDateTime.now())
					.serverURL("localhost:10000")
					.brokers(Arrays.asList("broker-1", "broker-2", "broker-3"))
					.build();
			em.persist(kafkaConnector);

			// TODO: 5-1. 1:1 단방향 관계 : Member -> MemberDetail - 이용자 상세정보 (User is reserved keyword)
			Member suhyuk = Member.builder().name("suhyuk").build();
			MemberDetail memberDetail1 = MemberDetail.builder().nickName("psyoblade").build();
			suhyuk.addMemberDetail(memberDetail1);
			em.persist(memberDetail1);
			em.persist(suhyuk);
			 */

			// TODO: 5-2. 1:1 양방향 관계 : Member -> MemberLocker - 이용자 락커

			/**
			// TODO: 6. 1:N 단방향 관계 : Team <- Member - 팀이 팀원이 채용한다 (1이 연관관계의 주인, FK를 가지는 테이블)
			//  EXPECTED : 팀은 독립적인 테이블이고, 멤버에만 팀의 외래키를 가진다
			Team dit = Team.builder().name("DataIngestionTeam").build();
			Member songhee = Member.builder().name("songhee").build();
			dit.hire(songhee);
			em.persist(dit);
			em.persist(songhee);
			 */

			/**
			// TODO: 7. 1:N 양방향 관계 : Member <- KafkaConnector - 이용자가 커넥터를 생성한다
			//  EXPECTED : 카프카 커넥터 테이블에 멤버 외부키가 존재합니다
			Member chiyoung = Member.builder().name("chiyoung").build();
			KafkaConnector kafkaConnector1 = KafkaConnector.builder().name("kafka-connector-1")
					.brokers(Arrays.asList("broker-4", "broker-5"))
					.serverURL("server-1")
					.build();
			KafkaConnector kafkaConnector2 = KafkaConnector.builder().name("kafka-connector-2")
					.brokers(Arrays.asList("broker-6", "broker-7"))
					.serverURL("server-2")
					.build();
			chiyoung.createKafkaConnector(kafkaConnector1);
			chiyoung.createKafkaConnector(kafkaConnector2);
			em.persist(kafkaConnector1);
			em.persist(kafkaConnector2);
			em.persist(chiyoung);

			System.out.println("##############");
			Member found = em.find(Member.class, 1L);
			found.getKafkaConnectors().forEach(kafkaConnector -> System.out.println(
					"kafkaConnector = " + kafkaConnector));
			System.out.println("##############");
			 */

			// TODO: 8. N:1 관계 : Member -> Team - 여러 이용자(N)가 팀(1)에 합류하다

			/**
			 */
			// TODO: 9. N:M 관계 : Team -> TeamTag <- Tag - N:M 대신 1:N + N:1 방식으로 대체
			//                    하나의 팀은 여러 역할을 가질 수 있고, 하나의 역할에 여러 팀이 할당될 수 있다
			TeamTag tagDev = TeamTag.builder().name("개발").build();
			TeamTag tagOp = TeamTag.builder().name("운영").build();
			em.persist(tagDev);
			em.persist(tagOp);

			Tag dev = Tag.builder().name("dev").build();
			Tag op = Tag.builder().name("op").build();
			Tag devOps = Tag.builder().name("devOps").build();
			dev.registerTag(tagDev);
			op.registerTag(tagOp);
			devOps.setTeamTags(Arrays.asList(tagDev, tagOp));
			em.persist(dev);
			em.persist(op);
			em.persist(devOps);

			Team devTeam = Team.builder().name("devTeam").build();
			Team opTeam = Team.builder().name("opsTeam").build();
			Team devOpsTeam = Team.builder().name("devOpsTeam").build();
			devTeam.setTeamTags(Collections.singletonList(tagDev));
			opTeam.setTeamTags(Collections.singletonList(tagOp));
			devOpsTeam.setTeamTags(Arrays.asList(tagDev, tagOp));
			em.persist(devTeam);
			em.persist(opTeam);
			em.persist(devOpsTeam);

			/**
			 */
			// 개발 태그를 가진 모든 팀 조회
			Tag foundTag = em.find(Tag.class, 1L);

			// devOpsTeam 이 가진 모든 태그 조회
			Team foundTeam = em.find(Team.class, 3L);
			foundTeam.getTeamTags().forEach(teamTag -> System.out.println("teamTag = " + teamTag));

			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			emf.close();
		}

	}

}
