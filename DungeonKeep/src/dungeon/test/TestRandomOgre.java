package dungeon.test;


import org.junit.Test;
import dungeon.logic.Board;
import dungeon.logic.Ogre;

public class TestRandomOgre {

	@Test(timeout=1000)
	public void testRandomOgreBehaviour(Ogre ogre, Board board){

		int preC,preL;

		boolean left=false, rigth=false, up=false, down=false;

		while(!left || !rigth || !up || !down){
			
			preC=ogre.getColumn();
			preL=ogre.getLine();
						
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

			
		}
		
	}

	@Test(timeout=1000)
	public void testRandomClubBehaviour(Ogre ogre, Board board){
	
		
		boolean left=false, rigth=false, up=false, down=false;

		
		
		while(!left || !rigth || !up || !down){
			
			ogre.setClub(ogre.getLine(), ogre.getColumn());		
			ogre.movingDirection();	
			ogre.moveClub(board);
			
			// ERRROOOOO
			
			if (ogre.getClubColumn()==ogre.getColumn()-1)
				left=true;

			else if (ogre.getClubColumn()==ogre.getColumn()+1)
				rigth=true;

			else if (ogre.getClubLine()==ogre.getLine()-1)
				up=true;
			
			else if (ogre.getClubLine()==ogre.getLine()+1)
				down=true;			
		}
		
	}
	

	
	
}
