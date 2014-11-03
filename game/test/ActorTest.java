package api;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ActorTest {
	MeleeActor a = new MeleeActor();
	@Test
	public void testAction() {
		int ret = a.action(20, 3);
		if(ret != 23){
			fail("wrong function return");
		}
		ret = a.action(10, 1);
		if(ret != 11){
			fail("wrong function return");
		}
		ret = a.action(30, 1);
		if(ret != 31){
			fail("wrong function return");
		}
	}

	@Test
	public void testSetDefending() {
		a.setDefending(10);
		a.setDefending('a');
	}

	@Test
	public void testOffensivePower() {
		int ret = a.offensivePower();
		if(ret != 0){
			fail("wrong function return");
		}
	}

}
