package HCS.server.model;

import HCS.shared.transferObjects.Prescription;
import HCS.shared.utility.Subject;

import java.util.ArrayList;

public interface PrescriptionModelServer extends Subject
{
  void createPrescription(Prescription prescription);
  ArrayList<Prescription>  getPrescriptions();
}
