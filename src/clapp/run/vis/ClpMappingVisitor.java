package clapp.run.vis;

import clapp.graph.ClpGraphMaker;
import clp.run.cel.graph.map.CompoundMapping;
import clp.run.cel.graph.map.GraphMapType;
import clp.run.cel.graph.map.MapOperator;
import clp.run.cel.graph.map.MappingVisitor;
import clp.run.cel.graph.map.SimpleMapping;
import clp.run.res.Resources;

public class ClpMappingVisitor implements MappingVisitor {

  private Resources res;
  private ClpGraphMaker graphMaker;

  public ClpMappingVisitor(Resources res, ClpGraphMaker gm) {
    this.res = res;
    this.graphMaker = gm;
  }

  @Override
  public void visitCompoundMapping(CompoundMapping x) {
    SimpleMapping smap = x.getSimpleMapping();
    ClpGraphMapTypeVisitor vis = new ClpGraphMapTypeVisitor(res);

    doMapping(vis, smap.getMapOperator(), smap.getGraphMapType(), x.getNode());

    if (x.isCompoundMapping()) {
      visitCompoundMapping(x.getCompoundMapping());
    }
  }

  @Override
  public void visitSimpleMapping(SimpleMapping x) {
    x.getMapOperator();
    ClpGraphMapTypeVisitor vis = new ClpGraphMapTypeVisitor(res);

    doMapping(vis, x.getMapOperator(), x.getGraphMapType(), graphMaker.getName());
  }


  private void doMapping(ClpGraphMapTypeVisitor vis, MapOperator op, GraphMapType type, String node) {
    type.accept(vis);
    switch (op) {
      case GETS:
        if (vis.isClappVariable()) {
          graphMaker.mapInput(node, vis.getVariable());
        }
        break;
      case SETS:
        if (vis.isClappVariable()) {
          graphMaker.mapOutput(node, vis.getVariable());
        }
        break;
      case EXCHANGES:
        break;
    }
  }
}
