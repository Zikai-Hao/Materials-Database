function graph_show(cif) {
	// setup component
	var crystalTransformer = new ChemDoodle.TransformCanvas3D('ribbonTransformer',360,360);
	// set the atom/bond representation to 'Ball and Stick', although other representations are also useful for zeolites
	crystalTransformer.specs.set3DRepresentation('Ball and Stick');
	// set the background color to black
	crystalTransformer.specs.backgroundColor = '#fffdfa';
	// declare an orthographic perspective to more easily see the zeolite pattern
	crystalTransformer.specs.projectionPerspective_3D = false;
	// set the line widths for the unit cell to be 1.5 pixels
	crystalTransformer.specs.crystals_unitCellLineWidth = 1.5;
	// set the unit cell line color to white
	crystalTransformer.specs.shapes_color = 'black';
	// set the unit cell line width to 2
	crystalTransformer.specs.shapes_lineWidth = 2;


	// load data from CIF file, unit cell is automatically generated
	var MAZfile = cif;
	// you can change the 1s here to generate a supercell
	var maz = ChemDoodle.readCIF(MAZfile, 1, 1, 1);
	crystalTransformer.loadContent([maz.molecule], [maz.unitCell]);
	
	}
