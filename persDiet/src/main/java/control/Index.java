package control;

import javax.faces.bean.ManagedBean;

@ManagedBean (name="index")
public class Index {

  public Index(){
  }

  public String getHello(){
    return "hello mangante";
  }
}
