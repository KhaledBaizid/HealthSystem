package HCS.Persistence;

import HCS.shared.transferObjects.PrescriptionType;

import java.util.ArrayList;

public interface ManagePrescriptionTypeDAO
{
  void createPrescriptionType(PrescriptionType prescriptionType);
  ArrayList<String> getPrescriptionsType();
}
