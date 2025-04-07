export class item {
    private _id: number;
    private _name: string;
    private _amount: number;
    private _collected: boolean;

    constructor(props?: {id: number, name: string, amount: number, collected: boolean}) {
        this._id = props?.id ?? 0;
        this._name = props?.name ?? "";
        this._amount = props?.amount ?? 0;
        this._collected = props?.collected ?? false;
    }


    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get amount(): number {
        return this._amount;
    }

    set amount(value: number) {
        this._amount = value;
    }

    get collected(): boolean {
        return this._collected;
    }

    set collected(value: boolean) {
        this._collected = value;
    }
}