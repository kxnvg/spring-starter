import dayjs from 'dayjs';

export interface IBook {
  id?: number;
  title?: string;
  publicationDate?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IBook> = {};
