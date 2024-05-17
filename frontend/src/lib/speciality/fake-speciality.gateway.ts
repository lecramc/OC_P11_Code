// fake-speciality.service.ts
import { SpecialityEntity } from "./speciality.entity";
import { SpecialityInterface } from "./speciality.interface";

export class FakeSpecialityGateway implements SpecialityInterface {
  getAllSpecilities(): Promise<SpecialityEntity[]> {
    return new Promise((resolve, reject) => {
      return resolve([
        { id: 1, name: "Cardiology" },
        { id: 2, name: "Neurology" },
        { id: 3, name: "Orthopedics" },
      ]);
    });
  }
}
