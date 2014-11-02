package api;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SpawnerTest {
	Spawner s = new Spawner();

	@Test
	public void testGetTeam() {
		s.setTeam(100);
		int ret = s.getTeam();
		if(ret != 100){
			fail("Wrong function Return");
		}

		s.setTeam(99);
		ret = s.getTeam();
		if(ret != 99){
			fail("Wrong function Return");
		}

		s.setTeam(00);
		ret = s.getTeam();
		if(ret != 0){
			fail("Wrong function Return");
		}
	}

	@Test
	public void testGetX() {
		s.x = 99;
		if(s.x != 99){
			fail("Wrong function Return");
		}
		s.x = -1;
		if(s.x != -1){
			fail("Wrong function Return");
		}
	}

}
