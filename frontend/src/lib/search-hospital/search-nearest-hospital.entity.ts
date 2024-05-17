export type SearchNearestHospitalEntity = {
  longitude: number;
  latitude: number;
  specialityId: number;
};

export type NearestHospitalEntity = {
  id: number;
  name: string;
  address1: string;
  address2: string;
  address3: string;
  city: string;
  postCode: string;
  latitude: number;
  longitude: number;
  availableBeds: number;
  specialities: { id: number; name: string }[];
};
