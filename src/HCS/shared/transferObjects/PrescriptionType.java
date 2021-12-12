package HCS.shared.transferObjects;

import java.io.Serializable;

public class PrescriptionType implements Serializable
{
  private String typeName;
  public PrescriptionType(String typeName)
  {
    this.typeName=typeName;
  }

  public String getTypeName()
  {
    return typeName;
  }
}
