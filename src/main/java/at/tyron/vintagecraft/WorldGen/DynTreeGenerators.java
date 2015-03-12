package at.tyron.vintagecraft.WorldGen;

import at.tyron.vintagecraft.WorldGen.EvolvingNatFloat.Function;
import at.tyron.vintagecraft.WorldProperties.EnumTree;

public class DynTreeGenerators {
	
	static EvolvingNatFloat branching(int num) {
		return EvolvingNatFloat.createIdentical(num);
	}
	
	public static void initGenerators() {
		// Reference: 
		/* new DynTreeTrunk(avgHeight, width, widthloss, branchStart, branchSpacing, branchVarianceSpacing, variance, numBranching, branchWidthMultiplier),
		 * new DynTreeBranch(anglevert, varianceAnglevert, anglehori, varianceAnglehori, spacing, varianceSpacing, widthloss, gravityDrag)
		 * new DynTreeBranch(NatFloat verticalAngle, NatFloat horizontalAngle, NatFloat spacing, float widthloss)
   		 */
		DynTreeGen test;
		DynTreeTrunk trunk;
		DynTreeBranch branch;
		
		EnumTree.BIRCH.setGenerators(test = new DynTreeGen(
			EnumTree.BIRCH, 
			null,
			trunk = new DynTreeTrunk(
//				0.8f,
				1f, 
				0.05f, 
				NatFloat.createGauss(0.22f, 0.01f), 
				NatFloat.createGauss(0.005f, 0.1f), 
				NatFloat.createInvGauss(NatFloat.PI / 4 + 0.1f, NatFloat.PI / 8), 
				NatFloat.createUniform(0, 2*NatFloat.PI),
				branching(3), 
				0.5f
			),
			branch = new DynTreeBranch(
				NatFloat.createInvGauss(NatFloat.PI / 4 + 0.1f, NatFloat.PI / 8),
				NatFloat.createUniform(0, 2*NatFloat.PI), 
				NatFloat.createGauss(0.01f, 0.01f), 
				0.06f
			)
		), null, null);
		
		/*GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IBlockState.class, new BlockStateSerializer());
        
        Gson gson = builder.create();
        
        System.out.println(gson.toJson(test));
		*/
		
		// Generates Trees like TFC Douglasfir 
		/*
		EnumTree.PURPLEHEARTWOOD.setGenerators(new DynTreeGen(
				EnumTree.PURPLEHEARTWOOD, 
				null,
				new DynTreeTrunk(
					1.2f,
					1f, 
					0.03f, 
					NatFloat.createUniform(0.5f, 0.05f),      // branchstart 
					NatFloat.createUniform(0f, 0f), 	      // branchspacing
					NatFloat.createGauss(0.7f, 0.4f),         // verticalangle
					NatFloat.createUniform(0, 2*NatFloat.PI), // horizontalangle
					16,
					1f,
					1f,
					0f,
					0f
				),
				new DynTreeBranch(
					NatFloat.createGauss(0.2f, 0.2f),
					NatFloat.createUniform(0, 2*NatFloat.PI), 
					NatFloat.createGauss(0f, 0f),
					NatFloat.createGauss(0f, 0f),
					0.1f,
					0f
				),
				0.8f
			), null, null);
		*/
		
		//new DynTreeTrunk(avgHeight, width, widthloss, branchStart, branchSpacing, verticalAngle, horizontalAngle, numBranching, branchWidthMultiplier, widthBranchLossBase, bend, bendCorrection)
		//new DynTreeBranch(verticalAngle, horizontalAngle, branchStart, spacing, widthloss, gravitydrag, branchWidthMultiplier)
		//DynTreeBranch(NatFloat verticalAngle, NatFloat horizontalAngle, NatFloat branchStart, NatFloat spacing, float widthloss, float gravitydrag)
		
		EnumTree.CRIMSONKINGMAPLE.setGenerators(new DynTreeGen(
			EnumTree.CRIMSONKINGMAPLE,
			null,
			new DynTreeTrunk(
				1f, 
				0.1f, 
				NatFloat.createGauss(0.5f, 0f), 
				NatFloat.createGauss(0f, 0f), 
				NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0), 
				NatFloat.createUniform(0, 2*NatFloat.PI),
				branching(8), 
				1f
			),
			new DynTreeBranch(
				NatFloat.createInvGauss(NatFloat.PI / 2 - 0.35f, 0),
				NatFloat.createUniform(0, 2*NatFloat.PI), 
				NatFloat.createGauss(0.01f, 0f), 
				0.17f
			),
			1.5f
		), null, null);
		
		//new DynTreeTrunk(width, widthloss, branchStart, branchSpacing, verticalAngle, horizontalAngle, numBranching, branchWidthMultiplier, widthBranchLossBase)
		
		EnumTree.PURPLEHEARTWOOD.setGenerators(new DynTreeGen(
				EnumTree.PURPLEHEARTWOOD, 
				new DynTreeRoot(
					NatFloat.createUniform(1.5f, 0.5f), // baseWidth, 
					NatFloat.createUniform(0.15f, 0f), // rootEnd
					NatFloat.createUniform(0f, 0f), // rootSpacing
					NatFloat.createUniform(3f, 1f),  // numbranching 
					0.3f,                              // widthloss
					EvolvingNatFloat.createUniform(0f, NatFloat.PI*2, Function.IDENTICAL, 0f),   // hor angle 
					EvolvingNatFloat.createUniform(NatFloat.PI-0.1f, 0.1f, Function.LINEAR, -0.001f),  // ver angle
					NatFloat.createUniform(0f, 0f),                  // branch ver angle
					NatFloat.createInvGauss(0f, NatFloat.PI / 2),      // branch hor angle
					NatFloat.createGauss(0.4f, 0.1f),  // branch spacing 
					NatFloat.createGauss(0.3f, 0.1f), //branchStart,  
					1f
				),
				new DynTreeTrunk(
					1f, 
					0.03f, 
					NatFloat.createUniform(0.4f, 0.05f),      // branchstart 
					NatFloat.createUniform(0f, 0f), 	      // branchspacing
					NatFloat.createGauss(0.7f, 0.4f),         // verticalangle
					NatFloat.createUniform(0, 2*NatFloat.PI), // horizontalangle
					branching(8),
					1f,
					0.92f
				),
				new DynTreeBranch(
					NatFloat.createGauss(0.3f, 0.2f),
					NatFloat.createUniform(0, 2*NatFloat.PI), 
					NatFloat.createGauss(0.4f, 0f),
					NatFloat.createGauss(1f, 0f),
					0.05f,
					0f,
					0.5f
				),
				0.8f
			), null, null);
		
		
		//new DynTreeTrunk(width, widthloss, branchStart, branchSpacing, verticalAngle, horizontalAngle, numBranching, branchWidthMultiplier, widthBranchLossBase, angleVert)
		
		//new DynTreeBranch(verticalAngle, horizontalAngle, branchStart, spacing, widthloss, gravitydrag, branchWidthMultiplier)
		//DynTreeBranch(NatFloat verticalAngle, NatFloat horizontalAngle, NatFloat branchStart, NatFloat spacing, float widthloss, float gravitydrag)
		//new DynTreeRoot(baseWidth, rootStart, numBranching, widthloss, horizontalAngle, verticalAngle, branchVerticalAngle, branchHorizontalAngle, branchSpacing, branchStart, widthBranchLossBase),

		//new DynTreeBranch(verticalAngle, horizontalAngle, branchStart, spacing, widthloss, numbranching, gravitydrag, branchWidthMultiplier, widthBranchLossBase)
		
	/*	EnumTree.KAPOK.setGenerators(new DynTreeGen(
			EnumTree.KAPOK,*/
			/*new DynTreeRoot(
				NatFloat.createUniform(1.5f, 0.5f), // baseWidth, 
				NatFloat.createUniform(0.25f, 0f), // rootEnd
				NatFloat.createUniform(0f, 0f), // rootSpacing
				NatFloat.createUniform(6f, 1f),  // numbranching 
				0.2f,                              // widthloss
				EvolvingNatFloat.createUniform(0f, NatFloat.PI*2, Function.IDENTICAL, 0f),   // hor angle 
				EvolvingNatFloat.createUniform(NatFloat.PI-0.1f, 0.1f, Function.LINEAR, -0.0013f),  // ver angle
				NatFloat.createUniform(0f, 0f),                  // branch ver angle
				NatFloat.createInvGauss(0f, NatFloat.PI / 2),      // branch hor angle
				NatFloat.createGauss(0.4f, 0.1f),  // branch spacing 
				NatFloat.createGauss(0.3f, 0.1f), //branchStart,  
				1f
			),*/
		/*	null,
			new DynTreeTrunk(
					10f, 
					0.5f, 
					NatFloat.createUniform(0.6f, 0.05f),      // branchstart 
					NatFloat.createUniform(0.3f, 0f), 	      // branchspacing
					NatFloat.createUniform(NatFloat.PI / 2 - 0.2f, 0f),         // verticalangle
					NatFloat.createUniform(0, 2*NatFloat.PI), // horizontalangle
					branching(1), //EvolvingNatFloat.createUniform(2f, 0, Function.LINEARREDUCE, 0.1f),
					0.5f,
					1f,
					//EvolvingNatFloat.createInvGauss(0f, 0.2f, Function.LINEAR, 0.0004f)
					EvolvingNatFloat.createIdentical(0f)
				),
				new DynTreeBranch(
					NatFloat.createUniform(0, NatFloat.PI * 2),
					NatFloat.createInvGauss(0f, NatFloat.PI / 2), 
					NatFloat.createGauss(0.6f, 0f),
					NatFloat.createGauss(0.1f, 0f),
					0.2f,
					branching(10), //EvolvingNatFloat.createUniform(8f, 0f, Function.LINEARREDUCE, 0.3f),
					0.05f,
					0.5f,
					1f
				),
				1f
		), null, null);
		
		*/
		
		
		
		EnumTree.PEAR.setGenerators(new DynTreeGen(
			EnumTree.PEAR,
			null,
			new DynTreeTrunk(
					1f, 
					0.07f, 
					NatFloat.createUniform(0.2f, 0.05f),     
					NatFloat.createUniform(0.2f, 0f), 	     
					NatFloat.createUniform(NatFloat.PI / 2 - 0.45f, 0f),   
					NatFloat.createUniform(0, 2*NatFloat.PI),
					EvolvingNatFloat.createUniform(2f, 0, Function.LINEAR, 0.1f),
					1f,
					0.92f,
					EvolvingNatFloat.createIdentical(0f)
				),
				new DynTreeBranch(
					NatFloat.createGauss(-0.3f, 0.1f),
					NatFloat.createInvGauss(0f, NatFloat.PI / 2), 
					NatFloat.createGauss(0.4f, 0f),
					NatFloat.createGauss(5f, 0f),
					0.15f,
					EvolvingNatFloat.createUniform(2f, 0f, Function.LINEAR, 2f),
					0f,
					0.8f,
					1f
				),
				1.3f
		), null, null);
		
		
		
		
		
		EnumTree.SPRUCE.setGenerators(new DynTreeGen(
			EnumTree.SPRUCE, 
			null,
			new DynTreeTrunk(
				1f, 
				0.03f, 
				NatFloat.createGauss(0.15f, 0.05f), 
				NatFloat.createGauss(0f, 0.1f), 
				NatFloat.createInvGauss(NatFloat.PI / 2 - 0.35f, 0), 
				NatFloat.createUniform(0, 2*NatFloat.PI),
				branching(10), 
				0.25f
			),
			new DynTreeBranch(
				NatFloat.createInvGauss(NatFloat.PI / 2 - 0.35f, 0),
				NatFloat.createUniform(0, 2*NatFloat.PI), 
				NatFloat.createGauss(5f, 0.05f), 
				0.03f
			),
			0.8f
		), 
		new DynTreeGen(
				EnumTree.SPRUCE, 
				null,
				new DynTreeTrunk(
					1f, 
					0.04f, 
					NatFloat.createUniform(0.15f, 0.05f), 
					NatFloat.createUniform(0.12f, 0.1f), 
					NatFloat.createInvGauss(NatFloat.PI / 2 - 0.35f, 0), 
					NatFloat.createUniform(0, 2*NatFloat.PI),
					branching(4), 
					0.25f,
					1f/*,
					NatFloat.createUniform(0f, 0f),
					EvolvingNatFloat.createUniform(0.1f, 0f, Function.QUADRATIC, 0.0005f)*/
				),
				new DynTreeBranch(
					NatFloat.createInvGauss(NatFloat.PI / 2 - 0.35f, 0),
					NatFloat.createUniform(0, 2*NatFloat.PI), 
					NatFloat.createGauss(5f, 0.05f), 
					0.03f
				),
				0.65f
			), null);
		
		EnumTree.MOUNTAINDOGWOOD.setGenerators(new DynTreeGen(
			EnumTree.MOUNTAINDOGWOOD, 
			null,
			new DynTreeTrunk(
				1f, 
				0.15f, 
				NatFloat.createGauss(0.5f, 0f), 
				NatFloat.createGauss(0f, 0f), 
				NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0), 
				NatFloat.createUniform(0, 2*NatFloat.PI),
				branching(8), 
				1f
			),
			new DynTreeBranch(
				NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0),
				NatFloat.createUniform(0, 2*NatFloat.PI), 
				NatFloat.createGauss(0.01f, 0f), 
				0.17f
			),
			0.8f
		), null, null);
		
		
		
		EnumTree.OAK.setGenerators(new DynTreeGen(
				EnumTree.OAK, 
				null,
				new DynTreeTrunk(
					1f, 
					0.15f, 
					NatFloat.createGauss(0.5f, 0f), 
					NatFloat.createGauss(0f, 0f), 
					NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0), 
					NatFloat.createUniform(0, 2*NatFloat.PI),
					branching(8), 
					1f
				),
				new DynTreeBranch(
					NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0),
					NatFloat.createUniform(0, 2*NatFloat.PI), 
					NatFloat.createGauss(0.01f, 0f), 
					0.17f
				),
				2.5f
			), null, null);
		
		
		
		// Reference: 
		/* new DynTreeTrunk(avgHeight, width, widthloss, branchStart, branchSpacing, branchVarianceSpacing, variance, numBranching, branchWidthMultiplier, widthBranchLossBase),
		 * new DynTreeBranch(verticalAngle, horizontalAngle, branchStart, spacing, widthloss, gravitydrag, branchWidthMultiplier)
   		 */
		
		
		EnumTree.MYRTLEBEECH.setGenerators(new DynTreeGen(
				EnumTree.MYRTLEBEECH, 
				null,
				new DynTreeTrunk(
					1f, 
					0.15f, 
					NatFloat.createGauss(0.2f, 0f), 
					NatFloat.createGauss(0f, 0f), 
					NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0), 
					NatFloat.createUniform(0, 2*NatFloat.PI),
					branching(1),
					3f
				),
				new DynTreeBranch(
					NatFloat.createUniform(NatFloat.PI / 2 - 0.4f, 0.5f),
					NatFloat.createUniform(0, 2*NatFloat.PI),
					NatFloat.createGauss(0.2f, 0f),
					NatFloat.createGauss(0.1f, 0f), 
					0.5f,
					-0.08f,
					0.25f
				),
				1.8f,
				1
			), null,
			new DynTreeGen(
				EnumTree.MYRTLEBEECH, 
				null,
				new DynTreeTrunk(
					1f, 
					0.1f, 
					NatFloat.createGauss(0.4f, 0f), 
					NatFloat.createGauss(0f, 0f), 
					NatFloat.createInvGauss(NatFloat.PI / 2 - 0.55f, 0), 
					NatFloat.createUniform(0, 2*NatFloat.PI),
					branching(1),
					2f
				),
				new DynTreeBranch(
					NatFloat.createGauss(NatFloat.PI / 2, 0.5f),
					NatFloat.createUniform(0, 2*NatFloat.PI),
					NatFloat.createGauss(0.6f, 0f),
					NatFloat.createGauss(0.1f, 0f), 
					0.5f,
					0.02f,
					0.5f
				),
				2.2f,
				1
			)
		);
		
		
		
		
		EnumTree.ACACIA.setGenerators(new DynTreeGen(
			EnumTree.ACACIA, 
			null,
			new DynTreeTrunk(
				1f,
				0.15f,
				NatFloat.createGauss(0.50f, 0.15f), 
				NatFloat.createUniform(0f, 0f), 
				NatFloat.createGauss(NatFloat.PI / 2 - 0.55f, 0f), 
				NatFloat.createUniform(0, 2*NatFloat.PI),
				branching(4), 
				1f,
				1f/*,
				NatFloat.createUniform(0.3f, 0.3f),
				EvolvingNatFloat.createUniform(0f, 0f, Function.QUADRATIC, -0.00027f)	*/			
			),
			new DynTreeBranch(
				NatFloat.createUniform(0, 0.5f),
				NatFloat.createUniform(0, 2 * NatFloat.PI), 
				NatFloat.createGauss(0f, 0f),
				NatFloat.createUniform(0.05f, 0.01f), 
				0.09f,
				0.01f,
				0.4f
			),
			1.5f
		), null, null);
		
		EnumTree.SCOTSPINE.setGenerators(
			// Normal
			new DynTreeGen(
				EnumTree.SCOTSPINE, 
				null,
				new DynTreeTrunk(
					1f,
					0.02f,
					NatFloat.createGauss(0.55f, 0.15f), 
					NatFloat.createUniform(0.3f, 0.005f), 
					NatFloat.createGauss(NatFloat.PI / 2, 0.3f), 
					NatFloat.createUniform(0, 2*NatFloat.PI),
					branching(4), 
					0.9f,
					1f
				),
				new DynTreeBranch(
					NatFloat.createUniform(0, 0.5f),
					NatFloat.createUniform(0, 2 * NatFloat.PI), 
					NatFloat.createGauss(0f, 0f),
					NatFloat.createUniform(0.25f, 0.1f), 
					0.027f,
					0.1f,
					0.4f
				),
				0.5f
			),
			
			
			// Reference: 
			/* new DynTreeTrunk(avgHeight, width, widthloss, branchStart, branchSpacing, branchVarianceSpacing, variance, numBranching, branchWidthMultiplier, widthBranchLossBase),
			 * new DynTreeBranch(verticalAngle, horizontalAngle, branchStart, spacing, widthloss, gravitydrag, branchWidthMultiplier)
	   		 */

			
			// Poor
			new DynTreeGen(
					EnumTree.SCOTSPINE, 
					null,
					new DynTreeTrunk(
						1f,
						0.15f,
						NatFloat.createGauss(0.50f, 0.15f), 
						NatFloat.createUniform(0f, 0f), 
						NatFloat.createGauss(NatFloat.PI / 2 - 0.55f, 0f), 
						NatFloat.createUniform(0, 2*NatFloat.PI),
						branching(4), 
						1f,
						1f,
						EvolvingNatFloat.createInvGauss(0, 2f, Function.LINEARREDUCE, 0.0007f)
					),
					new DynTreeBranch(
						NatFloat.createUniform(0, 0.5f),
						NatFloat.createUniform(0, 2 * NatFloat.PI), 
						NatFloat.createGauss(0.2f, 0f),
						NatFloat.createUniform(0.2f, 0.01f), 
						0.2f,
						0.01f,
						0.4f
					),
					1.5f
			),
			// Lush
			new DynTreeGen(
				EnumTree.SCOTSPINE, 
				null,
				new DynTreeTrunk(
					1f, 
					0.02f, 
					NatFloat.createGauss(0.3f, 0.1f), 
					NatFloat.createUniform(0.025f, 0.01f), 
					NatFloat.createGauss(NatFloat.PI / 2, 0.3f), 
					NatFloat.createUniform(0, 2*NatFloat.PI),
					branching(2), 
					0.5f
				),
				new DynTreeBranch(
					NatFloat.createUniform(0, 0.5f),
					NatFloat.createUniform(0, 2 * NatFloat.PI), 
					NatFloat.createGauss(0f, 0f),
					NatFloat.createUniform(0.15f, 0.08f), 
					0.027f,
					0.1f,
					0.5f
				),
				0.5f
			)
		);
		
		
		
		
		EnumTree.ELEPHANTTREE.setGenerators(
			new DynTreeGen(
					EnumTree.ELEPHANTTREE, 
					null,
					new DynTreeTrunk(
						1f,
						0.15f,
						NatFloat.createGauss(0.30f, 0.15f), 
						NatFloat.createUniform(0f, 0f), 
						NatFloat.createGauss(NatFloat.PI / 2 - 0.55f, 0f), 
						NatFloat.createUniform(0, 2*NatFloat.PI),
						branching(4), 
						1f,
						1f,
						EvolvingNatFloat.createInvGauss(0, 2f, Function.LINEARREDUCE, 0.0007f)

					),
					new DynTreeBranch(
						NatFloat.createUniform(5f, 0.5f),
						NatFloat.createUniform(0, 2 * NatFloat.PI), 
						NatFloat.createGauss(0.3f, 0f),
						NatFloat.createUniform(0.2f, 0.01f), 
						0.3f,
						0.01f,
						0.4f
					),
					1.5f
			), null, null
		);
		
		
		
		
		
		EnumTree.JOSHUA.setGenerators(
				new DynTreeGen(
						EnumTree.JOSHUA, 
						null,
						new DynTreeTrunk(
							4f,
							0.5f,
							NatFloat.createGauss(0.3f, 0.15f), 
							NatFloat.createUniform(0.2f, 0f), 
							NatFloat.createUniform(NatFloat.PI / 2 - 0.7f, 0f), 
							NatFloat.createUniform(0, 2*NatFloat.PI),
							branching(3), 
							1f,
							1f,
							EvolvingNatFloat.createGauss(0, 0.2f, Function.IDENTICAL, 0f)

						),
						new DynTreeBranch(
							NatFloat.createUniform(5f, 0.5f),
							NatFloat.createUniform(0, 2 * NatFloat.PI), 
							NatFloat.createUniform(5f, 0f),
							NatFloat.createUniform(5f, 0f), 
							1f,
							-0.8f,
							0f
						),
						0.7f
				), null, null
			);
		
		
		// Reference: 
		/* new DynTreeTrunk(width, widthloss, branchStart, branchSpacing, verticalAngle, horizontalAngle, numBranching, branchWidthMultiplier, widthBranchLossBase, bend, bendCorrection)
		 * new DynTreeBranch(verticalAngle, horizontalAngle, branchStart, spacing, widthloss, gravitydrag)
   		 */
		
		/*
		EnumTree.COCONUTPALM.setGenerators(new DynTreeGen(
			EnumTree.COCONUTPALM, 
			null,
			trunk = new DynTreeTrunk(
				1f, 
				0.07f, 
				NatFloat.createGauss(0.25f, 0.01f), 
				NatFloat.createGauss(0f, 0f), 
				NatFloat.createUniform(NatFloat.PI / 4, 0), 
				NatFloat.createUniform(0, 2*NatFloat.PI),
				16, 
				1f,
				0.3f,
				0.5f,
				0.05f
			),
			branch = new DynTreeBranch(
				NatFloat.createInvGauss(NatFloat.PI / 4 + 0.1f, NatFloat.PI / 8),
				NatFloat.createUniform(0, 2*NatFloat.PI), 
				NatFloat.createUniform(4f, 0),
				NatFloat.createGauss(5f, 0f), 
				0.05f,
				0.2f
			)
		), null, null);*/
		 
	}	

}