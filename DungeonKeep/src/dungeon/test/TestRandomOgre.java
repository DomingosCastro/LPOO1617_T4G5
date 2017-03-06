package dungeon.test;

import static org.junit.Assert.*;
import org.junit.Test;
import dungeon.logic.Board;
import dungeon.logic.Ogre;

public class TestRandomOgre {

	@Test(timeout=1000)
	public boolean testRandomOgreBehaviour(Ogre ogre, Board board){

		boolean result=false;
		
		int preC=ogre.getColumn();
		int preL=ogre.getLine();

		boolean left=false, rigth=false, up=false, down=false;

		while(! left || !rigth || !up || !down){
			ogre.movingDirection();			
			ogre.moveCharacter(board);


			if (ogre.getColumn()==preC-1)
				left=true;

			else if (ogre.getColumn()==preC+1)
				rigth=true;

			else if (ogre.getLine()==preL-1)
				up=true;
			else if (ogre.getLine()==preL+1)
				down=true;

			//else fail("Did not move");
		}

		if( !left || !rigth || !up || !down)
			fail("Did not move");
		else result=true;

		return result;
		
	}

}
