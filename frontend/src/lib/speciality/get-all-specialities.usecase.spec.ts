import { describe, it, expect, beforeEach } from "vitest";
import { FakeSpecialityGateway } from "./fake-speciality.gateway";
import { GetAllSpecialitiesUseCase } from "./get-all-specialities.usecase";
import { SpecialityEntity } from "./speciality.entity";
import { SpecialityInterface } from "./speciality.interface";

describe("Get all specialities usecase", () => {
  let useCase: GetAllSpecialitiesUseCase;
  let fakeGateway: SpecialityInterface;

  beforeEach(() => {
    fakeGateway = new FakeSpecialityGateway();
    useCase = new GetAllSpecialitiesUseCase(fakeGateway);
  });

  it("should return a list of specialities", async () => {
    // Given
    const expectedSpecialities: SpecialityEntity[] = [
      { id: 1, name: "Cardiology" },
      { id: 2, name: "Neurology" },
      { id: 3, name: "Orthopedics" },
    ];

    // When
    const actualSpecialities = await useCase.execute();

    // Then
    expect(actualSpecialities).toEqual(expectedSpecialities);
  });
});
