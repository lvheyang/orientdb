/*
 * Copyright 2013 Orient Technologies.
 * Copyright 2013 Geomatys.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orientechnologies.orient.core.sql.model;

import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.metadata.schema.OClass;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class OInferior extends OExpressionWithChildren{
  
  public OInferior(OExpression left, OExpression right) {
    this(null,left,right);
  }

  public OInferior(String alias, OExpression left, OExpression right) {
    super(alias,left,right);
  }
  
  public OExpression getLeft(){
    return children.get(0);
  }
  
  public OExpression getRight(){
    return children.get(1);
  }
  
  @Override
  protected String thisToString() {
    return "(<)";
  }

  @Override
  public Object evaluate(OCommandContext context, Object candidate) {
    final Integer v = compare(getLeft(),getRight(),context,candidate);
    return (v == null) ? false : (v < 0) ;
  }

  @Override
  public OSearchResult searchIndex(OSearchContext searchContext) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Object accept(OExpressionVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return super.equals(obj);
  }
  
  /**
   * Generic compare method
   *
   * @param object
   * @return Integer or null
   */
  static Integer compare(OExpression left, OExpression right, OCommandContext context, Object candidate) {
    final Object objleft = left.evaluate(context, candidate);
    if (!(objleft instanceof Comparable)) {
      return null;
    }
    final Object objright = right.evaluate(context, candidate);
    return compare(objleft, objright);
  }
  
  static Integer compare(Object objleft, Object objright) {

    if (!(objleft instanceof Comparable)) {
      return null;
    }

    if (objright == null) {
      return null;
    }
    
    if(objleft instanceof Number && objright instanceof Number){
      final Double dl = (Double)((Number)objleft).doubleValue();
      final Double dr = (Double)((Number)objright).doubleValue();
      return dl.compareTo(dr);
    }
    
    return ((Comparable) objleft).compareTo(objright);
  }

  @Override
  public OInferior copy() {
    return new OInferior(alias, getLeft(),getRight());
  }
  
}