import {
  NearestHospitalEntity,
  SearchNearestHospitalEntity,
} from "./search-nearest-hospital.entity";
import { GetNearestHospitalUseCase } from "./get-search-nearest-hospital.usecase";
import { FakeSearchNearestHospitalGateway } from "./fake-search-neared-hospital.gateway";
import { describe, it, expect, beforeEach } from "vitest";
describe("GetNearestHospitalUseCase", () => {
  let useCase: GetNearestHospitalUseCase;
  let fakeService: FakeSearchNearestHospitalGateway;

  beforeEach(() => {
    fakeService = new FakeSearchNearestHospitalGateway();
    useCase = new GetNearestHospitalUseCase(fakeService);
  });

  it("should return the nearest hospital when coordinates do not exactly match but are close", async () => {
    // Given
    const research: SearchNearestHospitalEntity = {
      longitude: 40.713,
      latitude: -74.006,
      specialityId: 1,
    };
    const expectedHospital: NearestHospitalEntity = {
      id: 1,
      name: "General Hospital",
      address1: "123 Main St",
      address2: "",
      address3: "",
      city: "New York",
      postCode: "10001",
      latitude: 40.712776,
      longitude: -74.005974,
      availableBeds: 50,
      specialities: [
        { id: 1, name: "Cardiology" },
        { id: 2, name: "Neurology" },
      ],
    };

    // When
    const actualHospital = await useCase.execute(research);

    // Then
    expect(actualHospital).toEqual(expectedHospital);
  });
});
